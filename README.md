###TEST the PRE-RELEASE!

The test simply consists of an executable in java 1.8

# Protocol__Sim
The network simulator is a small framework that can allow
to implement networking logics in a simulative java environment

I tried to make the mechanics as simple and flexible as possible 
so that it can be easy to implement more complex mechanisms 
(protocols, devices, real-time query)

##todo:
* write some default configuration for demostration purposes
* diversify devices (computer, hub, switch, router)
* implement device status dialogs
* implement connection simulations starting from the most obsolete (ex. via hubs)

## Class index:
* Board
* Sprite
* Device
* Package
* WireLock
* ConfigProvider
* User_Interface
* Main

## Class description:


**Board**: 

It is a JPanel, implements action Listener and aggregates a private KeyAdapter
  
* private initBoard() : void

Utility method for object construction

* public paintComponent(Graphics) : void

Override the super method paintComponent use drawObjects

* private drawObjects(Graphics) : void

Physically draw board components

* public actionPerformed(ActionEvent) : void

The loop routine

* private running() : void

Check internally isRunning

* private updateNetwork() : void

Check moved devices and update

* private updatePackage() : void

Check moved package and update

* private updateComputerUnits() : void

Check Devices and update

* public checkCollisions() : void

Check for collision between devices or between devices and
packages


**Sprite**

It is the base sprite

* public Sprite(int, int)
  
Construct the sprite by coordinates

* protected getImageDimensions() : void

Get the sprite textures dimentions

* protected loadImage(String ) : void

Set image by local address

* public getBounds() : Rectangle

Get a collider by sprite bounds

* public getImage() : Image

* public getX() : int

* public getY() : int

* public isVisible() : boolean

* public setVisible(Boolean) : void

**Device:Sprite**

* private initCraft() : void

Craft the device unit

* public move() : void 

Move device around using keys

* public getPackageOut() : List<Package> 
  
Get list of package out

* public getLocks() : Vector<WireLock>
  
Get device Locks

* public getSentPackage() : int
  
Get number of package sent

* public addConnection(int nLock, WireLock lock) : void
  
Add a connection

* public keyPressed(KeyEvent e) : void 
  
On key pressed (Directionals keys mapped)

* public keyReleased(KeyEvent e) : void 
  
On key released (Directionals mapped)

* public sendPacket() : void
  
The packages spawner

* public initWireLock() : void

Initialize the Device Locks


