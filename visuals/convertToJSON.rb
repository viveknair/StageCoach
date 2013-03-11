def convertToJSON

  characters = [
      "NAZAROVNA and EFIMOVNA",
      "EGOR MERIK",
      "BORTSOV'S WIFE'S COACHMAN",
      "FEDYA",
      "KUSMA",
      "TIHON EVSTIGNEYEV",
      "SAVVA",
      "MARIA EGOROVNA"
  ]
  
  indexHash = Hash[characters.map.with_index.to_a]

  puts indexHash.to_s
  str = ""
  lines = IO.readlines("adjacency_output_chekhov") 
  parsedJSON = ""
  currentSource = -1 
  lines.each do |line|
     tokens = line.split(",")
     puts tokens.size
     if tokens.size == 1
       currentSource = indexHash[tokens[0].strip]
     else 
       str = "{source:#{currentSource.to_s}, target:#{indexHash[tokens[0].strip].to_s}, value:#{tokens[1].strip}},\n"
     end
     parsedJSON += str
  end

  puts parsedJSON
end

convertToJSON
