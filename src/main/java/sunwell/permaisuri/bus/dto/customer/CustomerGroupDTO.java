//package sunwell.permaisuri.bus.dto.customer;
//
//
///*
// * CustomerGroup.java
// *
// * created 22 April 2014
// */
//
//import java.io.Serializable;
//
//import java.util.List;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityManager;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Query;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//import sunwell.permaisuri.core.entity.cred.UserGroup;
//import sunwell.permaisuri.core.entity.customer.CustomerGroup;
//
//public class CustomerGroupDTO implements Serializable
//{
//    private long systemId;
//    private String name;
//    private Double addPrice;
//    private String memo;
//
//    public CustomerGroupDTO ()
//    {
//    }
//    
//    public CustomerGroupDTO (CustomerGroup _cg)
//    {
//    		setData(_cg);
//    }
//    
//    public void setData(CustomerGroup _cg) {
//    		systemId = _cg.getSystemId();
//    		name = _cg.getName();
//    		addPrice = _cg.getAddPrice();
//    		memo = _cg.getMemo();
//    }
//    
//    public CustomerGroup getData() {
//    		CustomerGroup cg = new CustomerGroup();
//    		cg.setSystemId(systemId);
//    		cg.setName(name);
//    		cg.setAddPrice(addPrice);
//    		cg.setMemo(memo);
//    		return cg;
//    }
//
//    public long getSystemId ()
//    {
//        return systemId;
//    }
//
//    public void setSystemId (long _systemid)
//    {
//        this.systemId = _systemid;
//    }
//
//    public String getName ()
//    {
//        return name;
//    }
//
//    public void setName (String _groupname)
//    {
//        this.name = _groupname;
//    }
//    
//    public double getAddPrice ()
//    {
//        return addPrice;
//    }
//
//    public void setAddPrice (double _add)
//    {
//        this.addPrice = _add;
//    }
//
//    public String getMemo ()
//    {
//        return memo;
//    }
//
//    public void setMemo (String _memo)
//    {
//        this.memo = _memo;
//    }
//}
