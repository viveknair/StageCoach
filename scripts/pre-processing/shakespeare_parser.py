#!/usr/local/bin/python

#Shakespeare EParser
#Dustin Ali Francis Janatpour
#SUID: 05580037

import urllib2, re, sys

class PlayLine:
    def __init__(self, speaker, rawline):
        self.speaker = speaker
        self.rawline = rawline
    def concatenate(self, new_str):
        self.rawline = self.rawline + ' ' + new_str

class Play:
    def __init__(self, title, play_url):
        self.SD_TOKEN = 'STAGE_DIRECTION'
        self.DESC_TOKEN = 'DESCRIPTION'
        self.title = title
        self.acts = []
        self.characters = []
        u = urllib2.urlopen(play_url)
        for line in u:
            if 'Act' in line and 'Scene' in line:
                nums = map(int, re.findall('\d', line))
                act_num = nums[0]
                if len(self.acts) < act_num:
                    self.acts.append([])
                scene_num = nums[1]
                suffix = re.findall('\"\w+\.\d+\.\d+\.html\"', line)[0]
                suffix = suffix[1:len(suffix) - 1]
                scene_url = play_url.replace('index.html', suffix)
                self.acts[act_num-1].append(self.parse_scene(scene_url, scene_num))
    def parse_scene(self, scene_url, scene_num):
        scene = []
        usc = urllib2.urlopen(scene_url)
        curr = PlayLine('', '')
        for line in usc:
            if 'NAME=speech' in line:
                line = line.replace(':', '')
                speaker = ''.join(re.findall('\<b\>(\w+\s*\w+)+<\/b\>', line))
                if not speaker in self.characters:
                    self.characters.append(speaker)
                curr = PlayLine(speaker, '')
            elif 'NAME=' in line:
                rawline = re.findall('\>.+\<\/A\>', line)[0]
                rawline = rawline.replace('>', '')
                rawline = rawline.replace('</A', '')
                curr.concatenate(rawline)
            elif '</blockquote>' in line:
                if len(curr.speaker) > 0: scene.append(curr)
            if '<i>' in line:
                rawline = re.findall('\<i\>.+\<\/i\>', line)[0]
                rawline = rawline.replace('<i>', '[')
                rawline = rawline.replace('</i>', ']')
                scene.append(PlayLine(self.SD_TOKEN, rawline))
            if '<h3>' in line or '<H3>' in line:
                rawline = re.findall('\>.+\<\/', line)[0]
                rawline = rawline.replace('>', '')
                rawline = rawline.replace('</', '')
                scene.append(PlayLine(self.DESC_TOKEN, rawline))
        return scene
    def to_string(self):
        splay = ''
        splay += 'TITLE: ' + self.title + '\n\n'
        splay += 'CHARACTERS\n'
        for ch in self.characters:
            splay += ch + ',\n'
        splay += '\n'

        for i in range(len(self.acts)):
            act_num = i + 1
            act = self.acts[i]
            splay += 'ACT ' + str(act_num) + '. '
            for j in range(len(act)):
                scene_num = j + 1
                scene = act[j]
                splay += 'Scene ' + str(scene_num) + '. '
                for pl in scene:
                    if pl.speaker == self.DESC_TOKEN:
                        splay += pl.rawline
                splay += '\n'
                for pl in scene:
                    if pl.speaker == self.SD_TOKEN:
                        splay += pl.rawline + '\n'
                    elif not pl.speaker == self.DESC_TOKEN:
                        splay += pl.speaker + '. '
                        splay += pl.rawline + '\n'
        return splay
        

def write_plays(url, dire):
    u = urllib2.urlopen(url)
    for line in u:
        if 'a href=' in line and not 'Poetry' in line:
            suffix = re.findall('\"(\w+)\/index\.html\"', line)[0]
            title = line.split('html\">')[1].split('<')[0]
            play_url = url + '/' + suffix + '/index.html'
            print play_url
            new_play = Play(title, play_url)
            f = open(dire + '/' + suffix + '.txt', 'w')
            f.write(new_play.to_string())
            f.close()

def main():
    url = 'http://shakespeare.mit.edu'
    dire = sys.argv[1]
    write_plays(url, dire)

#Make it executable
if __name__ == "__main__":
    main()
