#!/usr/local/bin/python

#CS224u quote extraction
#Dustin Ali Francis Janatpour
#SUID: 05580037

import sys, re

def annotate_quotes(p, s):
    numget = re.compile('\{\d+\}')
    p_new = []
    start = s
    end = s
    for q in p:
        nums = numget.findall(q)
        for n in range(len(nums)):
            brack = nums[n]
            q = q.replace(brack, '')
            nums[n] = int(brack[1:(len(brack)-1)])
        if len(nums) > 0:
            start = nums[0]
            end = nums[len(nums) - 1]
        else:
            #There's a better way to do this
            start = end + 1
            end = start
        p_new.append([start, end, q])
    return [p_new, end]

def mark_sentences(lines):
    punclist = ['.', '?', '!']
    count = 1
    linelist = list(lines)
    for i in range(len(linelist)):
        c = linelist[i]
        if c in punclist:
            linelist[i] = c + '{' + str(count) + '}'
            count += 1
    return "".join(linelist)


def parse_quotes(lines):
    lines = lines.replace('\xe2\x80\x9d', '"')
    lines = lines.replace('\xe2\x80\x9c', '"')
    lines = lines.replace('\n\n', "PARAGRAPH_BREAK")
    lines = lines.replace('\n', ' ')
    lines = lines.replace("PARAGRAPH_BREAK", '\n')
    lines = lines.replace('...', '')

    lines = mark_sentences(lines)

    pgraphs = lines.split('\n')
    quotes = []
    
    last = 0

    for p in pgraphs:
        qlist = parse_quotes_from_paragraph(p, last)
        quotes_p = qlist[0]
        last = qlist[1]
        for q in quotes_p:
            quotes.append(q)
    return quotes

def parse_quotes_from_paragraph(p, s):
    p = ' ' + p
    p = p.split('"')
    plist = annotate_quotes(p, s)
    p = plist[0]
    quotes = []
    for i in range(len(p)):
        if i%2 == 1:
            quotes.append(p[i])
    return [quotes, plist[1]]

def get_quotes(infile):
    f = open(infile)
    lines = f.read()
    f.close()
    return parse_quotes(lines)

def output_quotes(outfile, quotes):
    f = open(outfile, 'w')
    count = 1
    for q in quotes:
         f.write(str(q[0]) + '-' + str(q[1]) + '\t')
         f.write(q[2] + '\n\n')
         count += 1
    f.close()


def main():
    infile = sys.argv[1]
    quotes = get_quotes(infile)
    count = 1
    outfile = sys.argv[2]
    output_quotes(outfile, quotes)

if __name__ == "__main__":
    main()