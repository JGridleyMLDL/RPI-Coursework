#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Sep 24 22:34:03 2019

@author: jaredgridley
The purpose of this program is to combine our module with our original wallpaper program. 
"""

from PIL import Image
from check2_helper import make_square

#This created the blank image where we will paste the other images. 
im1 = Image.new('RGB', (512, 512))

#This calls each of the 4 images and resized it to 256x256
filename1 = ('ca.jpg')
ca = Image.open(filename1)
squared1 = make_square(ca)
resized_ca = squared1.resize( (256, 256) )

filename2 = ('im.jpg')
im = Image.open(filename2)
squared2 = make_square(im)
resized_im = squared2.resize( (265, 256) )

filename3 = ('hk.jpg')
hk = Image.open(filename3)
squared3 = make_square(hk)
resized_hk = squared3.resize( (256, 256) )

filename4 = ('bw.jpg')
bw = Image.open(filename4)
squared4 = make_square(bw)
resized_bw = squared4.resize( (256, 256) )

#This pastes each of the images into the blank image
im1.paste( resized_ca, (0, 0))

im1.paste( resized_im, (256, 0))

im1.paste( resized_hk, (0, 256))

im1.paste( resized_bw, (256, 256))

#Showing the final image
im1.show()
