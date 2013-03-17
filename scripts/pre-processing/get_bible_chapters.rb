require 'net/http'

def get_bible_chapter(num)
  string_num = num < 10 ? "0" + num.to_s : num.to_s
  source = Net::HTTP.get('gutenberg.org', "/zipcat2.php/7999/7999-h/book#{string_num}.htm")
  File.open("/Users/viveknair/Dropbox/narrative_analysis/input/bible/#{string_num}.txt", 'w+') {|f| f.write(source) }
end

def get_bible
  for i in (1..66) 
    get_bible_chapter(i)  
  end
end

get_bible
