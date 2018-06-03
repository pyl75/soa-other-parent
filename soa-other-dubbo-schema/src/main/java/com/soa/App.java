package com.soa;

import com.soa.other.spring.schema.People;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.event.PaintEvent;

/**
 * Spring自定义标签
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("MyName.xml");
        People people = (People) context.getBean("agan");
        System.out.println(people.toString());
    }
}
