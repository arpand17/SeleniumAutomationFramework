package com.ea.framework.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.ea.framework.utils.ExcelUtils;

public class MethodInterceptor implements IMethodInterceptor{

	
	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		List <Map<String,String>> testdetail = ExcelUtils.getTestDetails();
		List<IMethodInstance> result = new ArrayList<>();
		
		for(int i =0;i<methods.size();i++) {
			for(int j =0;j<testdetail.size();j++){
				if(methods.get(i).getMethod().getMethodName().equalsIgnoreCase(testdetail.get(j).get("testname")))
				{
					if(testdetail.get(j).get("execute").equalsIgnoreCase("yes"))
					{
						methods.get(i).getMethod().setDescription(testdetail.get(j).get("testdescription"));
						methods.get(i).getMethod().setPriority(Integer.parseInt(testdetail.get(j).get("priority")));
						result.add(methods.get(i));
					}
				}
			}
		}
		
		return result;
	}

}
