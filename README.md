# Protocol__Sim
Connectivity Multi Protocol simulator 

## Class index:
* Board
* Sprite
* Device
* Package
* enum Direction
* WireLock
* ConfigProvider
* User_Interface
* Main

## Class description:

###Board: 

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

###Sprite

It is the base sprite

* public Sprite(int, int)
  
construct the sprite by coordinates

* protected getImageDimensions() : void

get the sprite textures dimentions

*protected loadImage(String ) : void

set image by local adress

* public getImage() : Image
  
* public getX() : int
  
*public getY() : int

*public isVisible() : boolean

*public setVisible(Boolean) : void

*public getBounds() : Rectangle

get a collider by sprite bounds



