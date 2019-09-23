# GuyMeng 街名

I pulled GeoJson data on the street geometries of Hong Kong from OpenStreetMaps. Then, I used a Python script that
I wrote to manually enter in metadata for each street regarding whether the language was English, Chinese, or Neutral.

#### What does it mean for a street to be English? Chinese? Neutral?
Every street has both an English and a Cantonese name. Sometimes, the street name would be a transliteration of the street name in the other language.

For example, `Hennessy Road` is `軒尼詩道` (pronounced Hey Ney See Dou\[road\]) in Cantonese. I would classify this street as
English. On the other hand, `龍和路 Lung Wo Road`, is designated as Chinese. `博覽道 Expo Drive` is Neutral since the street names
are direct translations of each other.

[The Map](https://api.mapbox.com/styles/v1/zyklotomic/ck0c21yjo4bgg1cm5yqv96aup.html?fresh=true&title=true&access_token=pk.eyJ1IjoienlrbG90b21pYyIsImEiOiJjanhqNTNodWIweGR5M29xbjZwbmswZHZjIn0.Eiul4956qjF8AYXdJL9qlg#13.4/22.290252/114.149604/0)

### Future Goals
Learn R to do more cool stuff with OpenStreetMaps data. Desire to learn R partly inspired by [this](https://erdavis.com/2019/09/20/the-beautiful-hidden-logic-of-cities-worldwide/).

### Errors
All the language data has been manually entered by hand. If there are any errors, PR's are welcome.

### Contributing
Since this was all entered manually, I have not completely finished geocoding the entirety of HK. If you would like to help out, here's how.

Export a section of GeoJSON data from OpenStreetMaps at https://overpass-turbo.eu/. Run `python street_analysis.py geojson_file`.
To break and save in the middle, enter `break`

```
$ python street_analysis.py data/geojson/hk-test.geojson
東翼前地 East Wing Forecourt
1 Eng, 2 Chi, 3 or other Neutral: 3
添馬露天劇場 Tamar Amphitheatre
1 Eng, 2 Chi, 3 or other Neutral: break
file name: hk-test-r1.geojson
```

### Inspiration
Before being ceded back to China on the midnight of July 1st, 1997, Hong Kong was British territory.
British control over the territory has left tangible influences on the city that remain to this day.
Hong Kong culture can be described as a fusion of Chinese and British characteristics.
I especially enjoyed watching [Vox's Borders series on Hong Kong](https://youtu.be/StW7oGSR_Mg). Johnny
Harris, the journalist behind
the series, does a great job of explaining this culture, way better than I can do. His work is truly amazing.

One example of this intersection of cultures I was interested in exploring was the street names of Hong Kong.
In the originally British territory of Hong Kong, discounting the New Territories up north, major arteries
with English names are interwoven with Cantonese roads. In the northern New Territories that were never under
British occupation, all the street names are in Cantonese. What I wanted to do was visualize the location
of all the British and Cantonese streets.
