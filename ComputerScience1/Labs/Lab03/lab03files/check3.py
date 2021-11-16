#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Sep 25 13:52:00 2019

@author: jaredgridley
"""

import wikimedia
from PIL import Image
from check2_helper import make_square

image_search = input("What images would you like to search for? ")


images = wikimedia.find_images(image_search, 4)

num = len(image_search)

print("You're query returned {} images".format(num))
images[0].show()
images[1].show()
images[2].show()
images[3].show()
print(type(images[0]))


#This created the blank image where we will paste the other images. 
im1 = Image.new('RGB', (512, 512))

#This calls each of the 4 images and resized it to 256x256

squared1 = make_square(images[0])
resized_ca = squared1.resize( (256, 256) )


squared2 = make_square(images[1])
resized_im = squared2.resize( (265, 256) )

squared3 = make_square(images[2])
resized_hk = squared3.resize( (256, 256) )

squared4 = make_square(images[3])
resized_bw = squared4.resize( (256, 256) )

#This pastes each of the images into the blank image
im1.paste( resized_ca, (0, 0))

im1.paste( resized_im, (256, 0))

im1.paste( resized_hk, (0, 256))

im1.paste( resized_bw, (256, 256))

#Showing the final image
im1.show()
