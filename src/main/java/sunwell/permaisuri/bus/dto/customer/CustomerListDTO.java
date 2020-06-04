///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// * ProdCategoryListDTO.java
// *
// * Created on Oct 18, 2017, 3:27:27 PM
// */
//
//package sunwell.permaisuri.bus.dto.customer;
//
//import java.util.LinkedList;
//
//
//import java.util.List;
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlRootElement;
//
//import sunwell.permaisuri.bus.dto.StandardDTO;
//import sunwell.permaisuri.core.entity.cred.UserGroup;
//import sunwell.permaisuri.core.entity.customer.Customer;
//
///**
// *
// * @author Benny
// */
//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
//public class CustomerListDTO extends StandardDTO
//{
//    private List<CustomerDTO> listCustomer;
//    
//    public CustomerListDTO() {
//        
//    }
//    
//    public CustomerListDTO(List<Customer> _users) {
//        setData (_users);
//    }
//    
//    public void setData(List<Customer> _groups) {
//        if(_groups != null && _groups.size () > 0) {
//            listCustomer = new LinkedList<> ();
//            for (Customer _ug : _groups) {
//                listCustomer.add (new CustomerDTO (_ug));
//            }
//        }
//        else
//            listCustomer = null;
//    }
//
//    /**
//     * @return the listUser
//     */
//    public List<CustomerDTO> getListCustomer ()
//    {
//        return listCustomer;
//    }
//
//    /**
//     * @param listCategory the listCategory to set
//     */
//    public void setListCustomer (List<CustomerDTO> _list)
//    {
//        this.listCustomer = _list;
//    }
//}
