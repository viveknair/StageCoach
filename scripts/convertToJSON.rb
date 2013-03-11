def convertToJSON

  characters = [
    "Ghost of Hamlet Father",
    "Marcellus",
    "Osric",
    "Ophelia",
    "Guildenstern",
    "I Player",
    "Lords",
    "Claudius",
    "Horatio",
    "Polonius",
    "1 Clown",
    "Messengers",
    "Rosencrantz",
    "Laertes",
    "Gertrude",
    "Sailors",
    "Marcellus",
    "Bernardo",
    "Fortinbras",
    "Laertes",
    "Francisco",
    "A Gentleman",
    "Voltimand",
    "Reynaldo",
    "A Captain.",
    "2 Clown"
  ]
  
  indexHash = Hash[characters.map.with_index.to_a]

  puts indexHash.to_s
  str = ""
  lines = IO.readlines(ARGV[0]) 
  parsedJSON = ""
  currentSource = -1 
  lines.each do |line|
     tokens = line.split(",")
     puts tokens.size
     if tokens.size == 1
        puts "Switching sources to #{tokens[0].strip}"
        if not indexHash[tokens[0].strip]
          puts "This one doesn't exist"
        end
       currentSource = indexHash[tokens[0].strip]
     else 
       str = "{source:#{currentSource.to_s}, target:#{indexHash[tokens[0].strip].to_s}, value:#{tokens[1].strip}},\n"
     end
     parsedJSON += str
  end

  puts parsedJSON
end

convertToJSON
