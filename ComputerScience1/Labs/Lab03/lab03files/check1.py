#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Sep 24 09:30:16 2019

@author: jaredgridley
The purpose of this lab is to make a wallpaper using 4 resized images. 
"""
from PIL import Image

#This created the blank image where we will paste the other images. 
im1 = Image.new('RGB', (512, 512))

#This calls each of the 4 images and resized it to 256x256
filename1 = ('ca.jpg')
ca = Image.open(filename1)
resized_ca = ca.resize( (256, 256) )

filename2 = ('im.jpg')
im = Image.open(filename2)
resized_im = im.resize( (265, 256) )

filename3 = ('hk.jpg')
hk = Image.open(filename3)
resized_hk = hk.resize( (256, 256) )

filename4 = ('bw.jpg')
bw = Image.open(filename4)
resized_bw = bw.resize( (256, 256) )

#This pastes each of the images into the blank image
im1.paste( resized_ca, (0, 0))

im1.paste( resized_im, (256, 0))

im1.paste( resized_hk, (0, 256))

im1.paste( resized_bw, (256, 256))

#Showing the final image
im1.show()