package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Zone {

	private List<Coordinate> zone = new ArrayList<Coordinate>();
	private List<Coordinate> blanks = new ArrayList<Coordinate>();
	private Set<Integer> missing = new HashSet<Integer>();
	private Set<Integer> Xaxis = new HashSet<Integer>();
	private Set<Integer> Yaxis = new HashSet<Integer>();
	private Set<Integer> nums = new HashSet<Integer>(
				Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
	
	
	public Zone(List<Coordinate> zone){
		this.zone=zone;
		populateWatch(this.zone);
	}
	
	public Zone(){}
	
	public void addCoordinate(Coordinate c){
		zone.add(c);
		populateWatch(zone);
	}
	
	public Set<Integer> getMissing(){
		return missing;
	}
	
	public Coordinate findVal(int val){
		for(Coordinate c: zone)
			if(c.getVal()==val)
				return c;
				
		return null;
	}
	
	public boolean isMissing(int m){
		if(missing.contains(m))
			return true;
		else 
			return false;
	}
	
	public List<Coordinate> getBlanks(){
		return blanks;
	}
	
	public Set<Integer> getXAxis(){
		return Xaxis;
	}
	
	public Set<Integer> getYAxis(){
		return Yaxis;
	}
	
	public void populateWatch(){
		populateWatch(zone);
	}
	
	public boolean isEmpty(){
		return zone.isEmpty();
	}
	
	private void populateWatch(List<Coordinate> zone){
		Set<Integer> got = new HashSet<Integer>();
		blanks.clear();
		missing.clear();
		missing.addAll(nums);
		
		for(Coordinate c: zone){
			Xaxis.add(c.getX());
			Yaxis.add(c.getY());
			if(c.getVal()!=0){
				got.add(c.getVal());
			}else{
				blanks.add(c);
			}
		}
		
		this.zone=zone;
		missing.removeAll(got);
	}

	@Override
	public String toString() {
		String ans="";
		for(Coordinate c: zone)
			ans+=c.toString()+" ";
				
		return ans;
	}
}
