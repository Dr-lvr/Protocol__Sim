package a_provider;

import java.util.Vector;

//just because java don't have pairs tuples and multimap, thanks java
public class ConnectionMap{
	private WireLock theLock;
	private Vector<WireLock> connections;

	public ConnectionMap(){
		this.connections=new Vector<>();
	}
	public void addConnection(WireLock lock){
		this.connections.add(lock);
	}
	public WireLock getTheLock(){
		return theLock;
	}
	public void setTheLock(WireLock theLock){
		this.theLock=theLock;
	}
	public Vector<WireLock> getConnections(){
		return connections;
	}
}
