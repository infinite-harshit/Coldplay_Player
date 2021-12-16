# Coldplay_Player

Steps to create Project : 

1. Create a new project 

2. Activity_main.xml : 
	# coldplay image 
	# about button
	# song button 
	# favname editext
	# savedfavsong button
	# newfavsong button 

3. Main activity.kt :   (sets content view of Activity_main.xml) 
	# includes a broadcast receiver to check internet connection (Broadcast receiver defined in Connectivityreceiver.kt)
	# starts animation on coldplay image
	# on clicking about button starts intent to About.kt
	# on clicking song button starts intent to PLaylist.kt
	# favname edittext is used to get the current favourite song of user  , which is stored using sharedpreference
	# on clicking savedfavsong button it displays the last saved favourite song from sharedpreference on edittext
	# on clicking newfavsong button it saves the current song name in sharedpreference 

4. intro.xml : 
	# includes the images of the members of the band with their names
	# displays the information about the band on a cardview 

5. About.kt : (sets content view of intro.xml )

6. List.xml : 
	# consists of a lsitview (of:image and text in linearlayout) which uses a cardview as a container

7.Playlist.kt : (sets content view of List.xml)
	# includes an Arrayadapter that helps to inflate the content of list on listview
	# includes three arrays of <Int>,<String> and <String> type that provides content for listview 
  	# on clicking the particular view of the list it gets redirected to the related song link on youtube.
	
	
	![image](https://user-images.githubusercontent.com/71807859/146318580-6e8312c0-59ab-4ad0-85c6-af881ffaa389.png)
	

