# Unofficial Bronx Zoo Audio Tour

Many museums have cheap digital audio tours where you use an app or device where you enter numbers at stations to listen to more info about what you’re looking at. In NYC the Met (https://www.metmuseum.org/visit/audio-guide) and MoMA have great ones; also in France there are bunch of great ones in the Loire Valley..

The Bronx Zoo doesn’t have an Audio Tour or anything like it (closest thing is founder William Temple Hornaday’s 1899 Guide to the New York Zoological Park; read here  https://babel.hathitrust.org/cgi/pt?id=uc1.c045667025 ). We’d like our Audio Tours to include both historical information about the park (which is weirdly hard to find except in primary sources like newspaper archives and Hornaday’s book; possibly because of scandals in its history – they did lots of things that wouldn’t be okay by today’s standards although they were progressive at the time) and information about the animals taken from the Encyclopedia of Life (eol.org) and Wikipedia (their information isn’t always reliable but it’s not copyrighted and worth using as long as you double check)

![Screenshot 2](https://github.com/jrandtc/UnofficialBronxZooAudioTour/blob/master/images/drawing2.jpg) ![Screenshot 1](https://github.com/jrandtc/UnofficialBronxZooAudioTour/blob/master/images/drawing1.jpg)

## 1.	What is/are the problem/s you want to solve? Please provide background based on research. 

There are currently no audiotours (or modern physical guides) to the Bronx Zoo.  Audio guides makes sightseeing much more rewarding. 

## 2.	Who are the users you are targeting? Please use a Persona to describe your users and draw a picture of your target user. 

Persona: Tom Soncook

Tom is a curious young adult who enjoys going to museums with his girlfriend on weekends; a much needed respite from his stressful job. Tom goes to the zoo with his girlfriend one weekend; he's interested in the animals at the Bronx zoo but he finds the educational plaques to be overwhelming in quantity and distracting from the animals. Tom would rather be looking at animals than reading plaques all day. Also, he finds that there's not enough information on many of the plaques.

Thankfully Tom and his date are nerds and have heard of the Unofficial Bronx Zoo Audioguide. Tom installs the audioguide on his android, and takes 2 bluetooth headsets for him and his date on the trip. Tom has a limit of 2gb per month of 4G, so he installed the app at home before he left.

Once Tom gets to the zoo he spends a day working though about half of the animals until he gets tired and it starts to get late. He see's that the app saves check marks next to all the animals he has already visited, so he knows he won't have to take notes to keep from repeating himself the next time he visits. 

## 3.	What are the major features of the solution? List the features.
- Lists every animal at the zoo
- Points to every animal / building in the zoo so one can find them
- Provides an audio tour description of each notable animal and building. 
- Provides fast links to learn all current academic information on each animal at Encyclopedia of Life and Wikipedia

## 4.	How is this solution unique? How does it relate to existing solutions on the market? What are three solutions similar or related to what you want to do in the Google Play Store? Do the research and know that you are probably not reinventing the wheel! Present these three solutions, a description and how they compare to yours. Provide screenshots of the existing solutions.
I am absolutely sure there is no existant audioguide for the Bronx Zoo. Naturally, there are countless app audioguides for various musuems and parks.  

The MET and MoMA both have mobile websites instead of mobile apps to provide audio tours. The MET does it in an extremely basic way, hosting a playlist off of Soundcloud for each of their exhibits. Users have to follow the tour in order of the playlist, and its quite tricky to navigate.

MoMA gives visiters more flexability than the MET, by using a system where users can enter numbers online to listen to audio information. This makes it so users to can enjoy the exhibits out of order. It also makes the tour easier to find. 

Rick Steve's, a famous tour guide, has a audio tour for Europe which is offered as an android app. Pictures are below:

![Screenshot 3](https://github.com/jrandtc/UnofficialBronxZooAudioTour/blob/master/images/0.png)
![Screenshot 1](https://github.com/jrandtc/UnofficialBronxZooAudioTour/blob/master/images/1.png)
![Screenshot 2](https://github.com/jrandtc/UnofficialBronxZooAudioTour/blob/master/images/2.png) 

## 5.	What technologies, APIs etc will you be using to develop this solution? Provide links and explanations.
- Some kind of database solution (probably XML)
- Some kind of tracking (probably Google Anaylitics)
- A Map API of some kind (for compasses and/or map
- Sound Playing / storing API (could be Soundcloud)
