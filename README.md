# Protocol__Sim

**Protocol__Sim** is a lightweight network simulator framework that allows you to implement networking logic inside a simulated Java environment.

The goal is to keep the mechanics **simple, modular, and flexible**, making it straightforward to extend the system with more complex behaviors (protocols, devices, real-time queries, etc.).

--

## Class Index

- `Board`  
- `Sprite`  
- `Device`  
- `Package`  
- `WireLock`  
- `ConfigProvider`  
- `User_Interface`  
- `Main`

---

# Class Descriptions

---

## **Board**

A `JPanel` that implements `ActionListener` and aggregates a private `KeyAdapter`.

### Methods

- **`private initBoard(): void`**  
  Utility method for initializing the board.

- **`public paintComponent(Graphics): void`**  
  Overrides `paintComponent` and uses `drawObjects`.

- **`private drawObjects(Graphics): void`**  
  Renders all board components.

- **`public actionPerformed(ActionEvent): void`**  
  Main loop routine.

- **`private running(): void`**  
  Checks whether the simulation is running.

- **`private updateNetwork(): void`**  
  Updates devices with movement changes.

- **`private updatePackage(): void`**  
  Updates moving packages.

- **`private updateComputerUnits(): void`**  
  Updates device state.

- **`public checkCollisions(): void`**  
  Detects collisions between devices or between devices and packages.

---

## **Sprite**

Base class for all drawable entities.

### Methods

- **`public Sprite(int x, int y)`**  
  Constructs sprite at coordinates.

- **`protected getImageDimensions(): void`**  
  Gets texture dimensions.

- **`protected loadImage(String path): void`**  
  Loads an image from local path.

- **`public getBounds(): Rectangle`**  
  Returns bounding box for collision detection.

- `public getImage(): Image`  
- `public getX(): int`  
- `public getY(): int`  
- `public isVisible(): boolean`  
- `public setVisible(Boolean): void`  

---

## **Device extends Sprite**

Represents a network device with movement, connections, and packet-handling abilities.

### Methods

- **`private initCraft(): void`**  
  Initializes device internals.

- **`public move(): void`**  
  Moves the device using keyboard input.

- **`public getPackageOut(): List<Package>`**  
  Returns list of outgoing packages.

- **`public getLocks(): Vector<WireLock>`**  
  Returns associated locks.

- **`public getSentPackage(): int`**  
  Number of sent packages.

- **`public addConnection(int nLock, WireLock lock): void`**  
  Adds a connection.

- **`public keyPressed(KeyEvent e): void`**  
  Handles directional key presses.

- **`public keyReleased(KeyEvent e): void`**  
  Handles directional key releases.

- **`public sendPacket(): void`**  
  Spawns and sends a packet.

- **`public initWireLock(): void`**  
  Initializes device locks.
