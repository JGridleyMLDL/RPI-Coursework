def make_square(Image):
    if Image.size[0] <= Image.size[1]:
        Length = Image.size[0]
    else:
        Length = Image.size[1]
        
    cropped_Image = Image.crop( (0, 0, Length, Length) )
    return cropped_Image
