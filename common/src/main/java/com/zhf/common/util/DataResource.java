package com.zhf.common.util;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;

/**
 * 数据和 resource数据的中间类
 * 
 * @author fei
 * 
 */
public class DataResource {

	 List<ResModel> list;
	 String[] nameArr;
	 int[] valueArr;
	
	 //DataResource mDataResource;
	
	 public DataResource(Context context,int resArrayName, int resArrayValue) {

		nameArr = context.getResources().getStringArray(resArrayName);

		valueArr = context.getResources().getIntArray(resArrayValue);
		
		list = new ArrayList<ResModel>();
		list.clear();
		for (int i = 0; i < nameArr.length; i++) {
			list.add(new ResModel(nameArr[i], valueArr[i], i));
		}

	}

	/**
	 * 根据名字取值
	 * @param name
	 * @return
	 */
	public int getValue(String name){
		for (int i = 0; i<list.size(); i++) {
			if(name.equals(list.get(i).name)){
				return list.get(i).value;
			}
		}
		return -1;
	}
	
	
	/**
	 * 根据值取名字
	 * @param value
	 * @return
	 */
	public String getName(int value){
		for (int i = 0; i<list.size(); i++) {
			if(value == list.get(i).value){
				return list.get(i).name;
			}
		}
		return "";
	}
	
	public int getValuePosition(int value){
		for (int i = 0; i < valueArr.length; i++) {
			if(value == valueArr[i])
				return i;
		}
		return -1;
	}
	
	public int getNamePosition(String name){
		for (int i = 0; i < nameArr.length; i++) {
			if(name.equals( nameArr[i]))
				return i;
		}
		return -1;
	}
	
	public List<ResModel> getResModelList() {
		return list;
	}

	public String[] getNameArray() {
		return nameArr;
	}

	public int[] getValueArray() {
		return valueArr;
	}
	
	public static class ResModel {
		public String name;
		public int value;
		public int position;

		public ResModel(String name, int value, int position) {
			this.name = name;
			this.value = value;
			this.position = position;
		}
	}
	
	
}
