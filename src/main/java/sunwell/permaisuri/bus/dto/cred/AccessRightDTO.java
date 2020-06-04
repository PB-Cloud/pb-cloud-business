///*
// * AccessRights.java
// *
// * Created on Feb 5, 2015, 4:54:52 PM
// */
//package sunwell.permaisuri.bus.dto.cred;
//
//import java.io.Serializable;
//
//
//import java.util.List;
//import java.util.Objects;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityManager;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.persistence.TypedQuery;
//import javax.xml.bind.annotation.XmlRootElement;
//
//import sunwell.permaisuri.core.entity.cred.AccessRights;
//
//
//public class AccessRightDTO implements Serializable 
//{
////	private UserGroupDTO owner;
//	private Integer taskType;    
//    private Integer accessBit;
//
//    public AccessRightDTO ()
//    {
//    }
//    
//    public AccessRightDTO (AccessRights _ac)
//    {
//    		setData(_ac);
//    }
//
//    public void setData(AccessRights _ac) {
//    		taskType = _ac.getTaskType();
//    		accessBit = _ac.getAccessBit();
//    }
//    
//    public AccessRights getData() {
//    		AccessRights ac = new AccessRights();
//    		if(taskType != null && accessBit != null)
//    			ac.setTaskTypeAndAccessBit(taskType, accessBit);
////    		if(owner != null)
////    			ac.setOwner(owner.getData());
//    		return ac;
//    }
//    
//    public void setTaskType (Integer _taskType)
//    {
//       taskType = _taskType;
//    }
//    
//    public Integer getTaskType ()
//    {
//        return taskType;
//    }
//    
//    public void setAccessBit (Integer _accBit)
//    {
//        accessBit = _accBit;
//    }
//    
//    public Integer getAccessBit ()
//    {
//        return accessBit;
//    }
//
//}
