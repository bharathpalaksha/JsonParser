package com.JsonParser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;


public class Parser 
{
	private static Parser pObj=null;
	private String jsonString=null;
	
	private Parser()
	{
		
	}
	
	static Parser getParser()
	{
		if(pObj==null)
		{
			pObj=new Parser();
		}
		return pObj;
	}
	
	// method partially implemented, converts Java object with primitive type data members into JSON
	public String toJson(Object obj)
	{
		jsonString="{";
		try
		{
			Class objClass=obj.getClass();                             // get class type of the object
			Field[] objFields= objClass.getDeclaredFields();           // get list of fields (member variables) in the object
			int i=0;
			for(Field field : objFields)                               // Iterate over it
			{
				if(i>=1)
				{
					jsonString=jsonString + ",";
				}
				Class type = field.getType();                          // get the type of the field
				if(type.isPrimitive())                                 // if type is of primitive type
				{
					jsonString=jsonString + "\"" + field.getName() + "\":\"" + field.get(obj) +"\"";
				}
				else if(type.isArray())                                // if it is a array type
				{ 
					decodeArray(field,obj);
					
				}
				else                                                   // if it is an object
				{
					
				}
				i++;
				
			}
			jsonString=jsonString + "}";
			return jsonString;
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	// Method to convert Array member into JSON string
	private void decodeArray(Field field, Object obj) 
	{
		jsonString=jsonString+"\""+field.getName()+"\":"+"[";
		try
		{
			int arrLength=Array.getLength(field.get(obj));
			for(int i=0;i<arrLength;i++)
			{
				if(i>=1)
				{
					jsonString=jsonString+",";
				}
				Object arrMember=Array.get(field.get(obj), i);
				if(arrMember instanceof Integer)
				{
					jsonString=jsonString+arrMember;
				}
				else if(arrMember instanceof Byte)
				{
					jsonString=jsonString+arrMember;
				}
				else if(arrMember instanceof Short)
				{
					jsonString=jsonString+arrMember;
				}
				else if(arrMember instanceof Long)
				{
					jsonString=jsonString+arrMember;
				}
				else if(arrMember instanceof Float)
				{
					jsonString=jsonString+arrMember;
				}
				else if(arrMember instanceof Double)
				{
					jsonString=jsonString+arrMember;
				}
				else if(arrMember instanceof Boolean)
				{
					jsonString=jsonString+arrMember;
				}
				else if(arrMember instanceof String)
				{
					jsonString=jsonString+"\""+arrMember+"\"";
				}
				else
				{
					// decodeObject(field,obj);
				}
			}
			jsonString=jsonString+"]";
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}

}
