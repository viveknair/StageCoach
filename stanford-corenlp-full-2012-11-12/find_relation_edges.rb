# @Author - Vivek Nair
# 	Should be executed in the same directory as stanford NLP version 
#		1.3.4 to access the essential JAVA files. The only argument is 
#		the path to the input file and the Reverb jar, in that order.
# 
# 	Usage: ruby find_relation_edges.rb path_to_reverb path_to_input_file 

require 'rexml/document'

# Invokes the Stanford CoreNLP directly through the
# commandline.
def invoke_stanford_nlp(arguments)
 `java -cp stanford-corenlp-1.3.4.jar:stanford-corenlp-1.3.4-models.jar:xom.jar:joda-time.jar:jollyday.jar -Xmx3g edu.stanford.nlp.pipeline.StanfordCoreNLP -annotators tokenize,ssplit,pos,lemma,ner,parse,dcoref -file #{arguments}`
end

# Invokes the Reverb system directly through the 
# commandline.
def invoke_reverb(reverb_loc, input_loc)
	stdout = `java -Xmx512m -jar #{reverb_loc} #{input_loc} > #{input_loc}.out`
end

# Constructs the coreference root from the Stanford
# XML representation
def construct_coref_root(arguments)
	last_path_name = arguments.split("/").last
	file = File.open("#{last_path_name}.xml", "r")
	doc = REXML::Document.new file

	root = doc.root
	document_root = root.elements["document"] 
	coreference_root = document_root.elements["coreference"] 
end

def construct_relation_structure(arguments)
	lines = IO.readlines("#{arguments}.out")
	rstruct = []
	lines.each do |line|
  	columns = line.split("\t")
		rstruct << columns.each_with_index.map { |value,index|  ((5..10).include? index or index == 1) ? value.to_f : value }
	end
	rstruct
end

def find_subject_object(coref_root, relation) 
	subject, object = nil, nil
	coref_root.elements.each_with_index do |coref_child, index| 
		mention = coref_child.elements["mention"]
		head = mention.elements["head"].text.to_i
		subject = head if (relation[5]..relation[6]).include? head
		object = head if (relation[9]..relation[10]).include? head
	end
	return subject, object
end

# Constructs the actual coreference relations
def construct_associations(coref_root, arguments)
	graph = {}
	relation_structure = construct_relation_structure(arguments)
	relation_structure.each do |relation|
		subject, object = find_subject_object(coref_root, relation)
		if subject and object
			graph["#{subject} #{object}"] = [] if not graph["#{subject} #{object}"]
			graph["#{subject} #{object}"] << relation
			next
		end

		temp = subject || object
		if temp 
			graph[temp] = [] if not graph[temp]
			graph[temp] << relation
		end
	end
	graph
end

# Constructs the relation graph
def construct_relation_graph
	return if (ARGV.size == 0) 
	reverb_loc = ARGV[0]
	input_file_loc = ARGV[1]
	invoke_reverb(reverb_loc, input_file_loc)
	invoke_stanford_nlp(input_file_loc)

	coref_root = construct_coref_root(input_file_loc)
	graph = construct_associations(coref_root, input_file_loc)
	File.open("#{input_file_loc}.kv", "w") do |file|
		key_value_rep = graph.inject("") { |result, key| result + "#{key[0]} : #{key[1..key.size].join(" ")} \n" }
		file.write(key_value_rep)
	end
end

construct_relation_graph
