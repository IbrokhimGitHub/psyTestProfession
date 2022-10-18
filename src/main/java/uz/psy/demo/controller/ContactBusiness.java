package uz.psy.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class ContactBusiness {
    public List<Contact> getContactList(){
        List<Contact> list=new ArrayList<>();
        list.add(new Contact("John","johN@as","india"));
        list.add(new Contact("name","dsa2@ds","russia"));
        list.add(new Contact("sina","@dsad","austr"));
        list.add(new Contact("Rock","das@sdd","uzb"));
        return list;
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Contact {
    private String name;
    private  String email;
    private String country;
}
