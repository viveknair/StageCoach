# @Author - Vivek Nair
# 	Implementation of the Elson adjacency metric for social network
#		analysis.	Should be executed in the same directory as stanford NLP version 
#		1.3.4 to access the essential JAVA files. 
#
#		Usage: ruby find_quote_referent.rb path/to/coreferents/xml path/to/quote/indices path/to/input/file

require 'rexml/document'

$delta = 3

def load_coreferents(input_path)
	file = File.open(input_path, "r")
	doc = REXML::Document.new file

	root = doc.root
	document_root = root.elements["document"] 
	coreference_root = document_root.elements["coreference"] 
end

def load_relation_edges(relation_path)
	coreference_edge = {}
	lines = IO.readlines(relation_path)	
	lines.each do |line|
		coref, edge = line.split(":")
		if edge
			coreference_edge[coref]	 = edge.split("\t").each_with_index.map do |value, index|
				((5..10).include? index or index == 1) ? value.to_f : value 
			end
		end
	end
	coreference_edge
end

def load_quote_indices(quote_path)
	lines = IO.readlines(quote_path)
	quotes = []
	lines.each do |line|
		puts line
		quote_info = line.split(":")
		raw_range = quote_info[0].split("-")
		range = (raw_range[0].to_i..raw_range[1].to_i)
		quotes << [range, quote_info[1]]
	end
	quotes
end

def write_association_hash(file_name, hash)
	File.open(file_name, "w+") do |file|
		results = hash.inject("") { |results, element| results += element[0] + " \t " + element[1..element.size] + "\n" }
		file.write(results)
	end
end

def find_quote_coreferent(quote, coreferents)
	coreferents.elements.each_with_index do |coref, index|
		mentions = coref.elements["mention"]
		mentions.elements.each do |mention|
			sen_index = mention.elements["sentence"]
			if quote[0].include? sen_index	
				return index
			end	
		end
	end
		
	nil
end

def compare_coreference(coreferents, coref1, coref2)
	sent_ind1 = []
	coreferents.elements.each_with_index do |corefer, index|
		if index == coref1
			corefer.elements.each { |mention| sent_ind1 << mention.elements["sentence"].text.to_i }
		end
	end

	sent_ind2 = []
	coreferents.elements.each_with_index do |corefer, index|
		if index == coref2
			corefer.elements.each { |mention| sent_ind2 << mention.elements["sentence"].text.to_i }
		end
	end

	difference = []
	sent_ind1.each do |ind1|
		sent_ind2.each do |ind2|
			difference << (ind1 - ind2).abs
		end
	end	

	difference.min
end

def construct_quote_weights
	if (ARGV.size < 3) 
 	 	puts "Usage:  ruby find_quote_referent.rb path/to/coreferents/xml path/to/quote/indices path/to/input/file"
		return 
	end

	coreferents = load_coreferents(ARGV[1])       # Read in the coreferents
	quotes = load_quote_indices(ARGV[2])  			   # Read in the quote indices
	input_file = ARGV[3]

	# First pass to find quote referents
	quote_coreferent = {}
	quotes.each do |quote|
		matching_coref = find_quote_coreferent(quote, coreferents)
		quote_coreferent[quote.to_s] = matching_coref if matching_coref
	end

	# Second pass to construct pairwise relations between
	# coreferents of quotes
	association_hash = {}
	quote_coreferent.each do |quote1, coref1|
		quote_coreferent.each do |quote2, coref2|
			distance = compare_coreference(coreferents, coref1, coref2)
			if distance < $delta
				association_hash["#{coref1} #{coref2}"] = 0 if not association_hash["#{quote1} #{quote2}"]
				association_hash["#{coref1} #{coref2}"] += 1 / distance
		
				# There is no concept of directionality in this metric
				association_hash["#{coref2} #{coref1}"] = 0 if not association_hash["#{quote2} #{quote1}"]
				association_hash["#{coref2} #{coref1}"] += 1 / distance
			end
		end	
	end

	write_association_hash(input_file, association_hash)
end

construct_quote_weights
