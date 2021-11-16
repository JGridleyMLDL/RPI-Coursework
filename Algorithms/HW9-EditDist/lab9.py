import sys
import numpy as np


def find_alignments(E, s1, s2):
    s1_alignment = ""
    s2_alignment = ""
    s = [len(E)-1, len(E[0])-1]
    while s != [0, 0]:
        possibilities = [(E[s[0]-1][s[1]], [s[0]-1, s[1]]), (E[s[0]][s[1]-1],
                                                             [s[0], s[1]-1]), (E[s[0]-1][s[1]-1], [s[0]-1, s[1]-1])]
        prev = min(possibilities)
        # Just need to fix the if tree.

        if s[0] == prev[1][0]:
            s1_alignment = "-" + s1_alignment
            s2_alignment = s2[s[1]-1] + s2_alignment
        elif s[1] == prev[1][1]:
            s1_alignment = s1[s[0]-1] + s1_alignment
            s2_alignment = "-" + s2_alignment
        else:
            s1_alignment = s1[s[0]-1] + s1_alignment
            s2_alignment = s2[s[1]-1] + s2_alignment

        s = prev[1]

    print("alignment:")
    print(s1_alignment, end="\n\n")
    print(s2_alignment, end="\n\n")


def editDistance(string1, string2):
    E = np.zeros(shape=(len(string1)+1, len(string2)+1))

    # Base Cases
    for i in range(len(string1)+1):
        E[i][0] = i
    for j in range(len(string2)+1):
        E[0][j] = j

    # Creating the rest of the matrix
    for i in range(1, len(string1)+1):
        for j in range(1, len(string2)+1):
            if string1[i-1] == string2[j-1]:
                diff = 0
            else:
                diff = 1
            E[i][j] = min(E[i-1][j]+1, E[i][j-1]+1, diff + E[i-1][j-1])

    edit_distance = E[-1][-1]
    print("edit distance = " + str(int(edit_distance))
    find_alignments(E, string1, string2)


if __name__ == "__main__":
    s1="SUNNY"
    s2="SNOWY"
    editDistance(s1, s2)
