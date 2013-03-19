def convertToJSON(filename)

  characters = []
  characters_hash = {} 

  lines = IO.readlines(filename)
  lines.each do |line|
    sline = line.strip
    first_match = sline[/Target Character: ([^,]*)/,1]
    second_match = sline[/Character: ([^,]*)/,1]
  
    if first_match 
      characters_hash[first_match.split] = 1
    elsif second_match
      characters_hash[second_match.split] = 1
    end
  end
    
  characters_hash.each do |key, value|
    characters << key
  end
  parsedJSON = "{\"nodes\":["

  characters.each_with_index do |character,index|
    parsedJSON += "{\"name\":\"#{character.join(" ")}\"}"
    parsedJSON += "," if not characters.size - 1 == index
  end

  parsedJSON += "], \"links\":["
 
  indexHash = Hash[characters.map.with_index.to_a]

  puts indexHash.to_s
  str = ""
  lines = IO.readlines(filename) 
  currentSource = -1 
  lines.each_with_index do |line,index|
     sline = line.strip
     first_match = sline[/Target Character: ([^,]*)/,1]
     second_match = sline[/Character: ([^,]*)/,1]

     if second_match and not first_match
        puts "Switching sources to #{second_match}"
        if not indexHash[second_match.split]
          puts "This one doesn't exist"
        end
       currentSource = indexHash[second_match.split]
     elsif first_match
      value = sline[/Value: ([^,]*)/,1]
      puts sline
       str = "{\"source\":#{currentSource.to_s}, \"target\":#{indexHash[second_match.split]}, \"value\":#{value.to_s}}"
       str += "," if not lines.size - 1 == index
       str += "\n"
     end
     parsedJSON += str
  end

  parsedJSON += "]}"

  parsedJSON
end

def convertDirectoryToJSON(directory) 
  Dir.foreach(directory) do |file|
    if file.length > 2 and not file == "json_networks"
      puts "fileName is #{file}"
      tokens = file.split(".")
      File.open("#{directory}/json_networks/#{tokens[0]}.json", 'w+') do |pointer|
        puts convertToJSON("#{directory}/#{file}")
        pointer.write(convertToJSON("#{directory}/#{file}"))
      end
    end
  end
end

puts convertDirectoryToJSON(ARGV[0])
