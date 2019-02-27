"""
Created on Tue Sep 11 07:59:00 2018

@author: MARTINS
"""
import random

class Logic:
    avail_moves = ["A", "B", "C", "D", "E", "F", "G", "H", "I"]
    played_moves = [[],[]]
    cor_moves = avail_cor = ["A", "C", "G", "I"]
    north, south, east, west = ["A","B","C"], ["G","H","I"], ["C","F","I"], ["A","D","G"]
    horMid, verMid, pograd, negrad = ["D","E","F"], ["B","E","H"], ["C","E","G"], ["A","E","I"]
    pose = [north, south, east, west, horMid, verMid, pograd, negrad]
    won = False
    draw = False

    def updater(self, move, player):
        self.avail_moves.remove(move)
        if (self.avail_cor.__contains__(move)):
            self.avail_cor.remove(move)
        self.played_moves[player].append(move)

    def isDecisive(self, result):
        reply = [False, []]
        for i in self.pose:
            count = 0
            alien = ''
            for j in i:
                if self.played_moves[result].__contains__(j):
                    count += 1
                else:
                    alien = j
            if (count == 2) and (self.avail_moves.__contains__(alien)):
                reply[0] = True
                reply[1].append(alien)
        return reply

    def isSmart(self):
        reply = [False, []]
        for i in self.avail_moves:
            self.played_moves[0].append(i)
            if (self.isDecisive(0)[0] == True) and (len(self.isDecisive(0)[1]) == 2):
                reply[0] = True
                reply[1].append(i)
            self.played_moves[0].remove(i)
        return reply

    def firstMove(self):
        if (self.avail_moves.__contains__("E")):
            fir = random.choice((self.avail_cor + ["E"]))                          #Play in any corner or middle
        else:
            fir = random.choice(self.avail_cor)                                #Play in any corner

        self.updater(fir, 0)
        print(fir)
        return fir

    def secondMove(self):
        if (self.isDecisive(1)[0] == True):
            sec = random.choice(self.isDecisive(1)[1])
        elif (self.avail_moves.__contains__("E")):                               #If center is free, play there
            sec = "E"
        else:
            sec = random.choice(self.avail_cor)                                #Play in any corner

        self.updater(sec, 0)
        print(sec)
        return sec

    def thirdMove(self):
        if (self.isDecisive(0)[0] == True):
            thi = random.choice(self.isDecisive(0)[1])
            self.won = True
        elif (self.isDecisive(1)[0] == True):
            thi = random.choice(self.isDecisive(1)[1])
        elif (self.isSmart()[0] == True):
            thi = random.choice(self.isSmart()[1])
        else:
            thi = random.choice(self.avail_moves)

        self.updater(thi, 0)
        print(thi)
        return thi

    def fourthMove(self):
        if (self.isDecisive(0)[0] == True):
            fou = random.choice(self.isDecisive(0)[1])
            self.won = True
        elif (self.isDecisive(1)[0] == True):
            fou = random.choice(self.isDecisive(1)[1])
        elif (self.isSmart()[0] == True):
            fou = random.choice(self.isSmart()[1])
        else:
            fou = random.choice(self.avail_moves)

        self.updater(fou, 0)
        print(fou)
        return fou

    def fifthMove(self):
        if (self.isDecisive(0)[0] == True):
            fif = random.choice(self.isDecisive(0)[1])
            self.won = True
        elif (self.isDecisive(1)[0] == True):
            fif = random.choice(self.isDecisive(1)[1])
        else:
            fif = random.choice(self.avail_moves)

        self.updater(fif, 0)
        print(fif)
        return fif

    def pMove(self, move):
        print(move)
        self.updater(move, 1)