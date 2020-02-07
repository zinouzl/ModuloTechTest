# ModuloTech Test

## Usage 

You can either download the zip folder and open it with Android studio or clone the repository and import it.


## Library and Frameworks

- `architecture components (LiveData + ViewModel)` For implementing MVVM architecture (you can use rx observables or Flowables instead of LiveData. But LiveData is a better choice since it is lifecycle aware)
- `Glide` to load images and bitmaps.
- `Espresso and JUnit` For testing (Unit tests and UI test).
- `Dagger2` For dependency injection
- `rx-java rx android` For background threading and handling database access.
- `Slider` A fancy SeekBar.


## App architecture
**Definition**

This app contains two main fragments.


### DeviceFragment

Where all available devices are listed and you can click on a device and change its parameters or swap it left or right to delete it.
Also, you can filter devices by type using Checkboxs on top.

![Sceenshot of devices fragment](https://github.com/zinouzl/DaggerProject/blob/master/screenshots/devices_view.PNG)
![Sceenshot of devices fragment2](https://github.com/zinouzl/DaggerProject/blob/master/screenshots/devices_light.PNG)
![Sceenshot of devices fragment](https://github.com/zinouzl/DaggerProject/blob/master/screenshots/devices_roller.PNG)

### UserFragment

Where you can see and edit user information.

Once you click on the edit button you can edit all user information besides, the save floating button will appear.
After editing, you can click the save button to save your changes subsequently the save button will disappear.

![Sceenshot of user fragment](https://github.com/zinouzl/DaggerProject/blob/master/screenshots/userMiseAj.PNG)
![Sceenshot of user fragment2](https://github.com/zinouzl/DaggerProject/blob/master/screenshots/time_user.PNG)
![Sceenshot of user fragment3](https://github.com/zinouzl/DaggerProject/blob/master/screenshots/userfrin.PNG)





