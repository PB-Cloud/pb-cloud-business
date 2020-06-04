//package sunwell.permaisuri.bus.dto.cred;
//
///**
// * UserGroup.java
// *
// * Created on March 24, 2014
// */
//
//import java.io.Serializable;
//
//
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.LinkedList;
//import java.util.List;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityManager;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Query;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//
//import sunwell.permaisuri.bus.dto.StandardDTO;
//import sunwell.permaisuri.core.entity.cred.AccessRights;
//import sunwell.permaisuri.core.entity.cred.UserGroup;
//
//public class UserGroupDTO extends StandardDTO implements Serializable
//{
//    public static final int ADMINISTRATOR = 1;
//    public static final int SALES_OFFICER = 2;
//
//    private Integer systemId;
//    private String name;
//    private String memo;
////    private List<AccessRightDTO> accessRights;
//
//    public UserGroupDTO ()
//    {
//    }
//    
//    public UserGroupDTO (UserGroup _ug)
//    {
//    		setData(_ug);
//    }
//    
//    public void setData(UserGroup _ug)
//    {
//    		systemId = _ug.getSystemId();
//    		name = _ug.getName();
//    		memo = _ug.getMemo();
//    		if(_ug.getAccessRights() != null && _ug.getAccessRights().size() > 0) {
//    			accessRights = new LinkedList<>();
//    			for(AccessRights ac : _ug.getAccessRights()) {
//    				accessRights.add(new AccessRightDTO(ac));
//    			}
//    		}
//    }
//    
//    public UserGroup getData() {
//    		UserGroup ug = new UserGroup();
//    		if(systemId != null)
//    			ug.setSystemId(systemId);
//    		ug.setName(name);
//    		ug.setMemo(memo);
//    		if(accessRights != null && accessRights.size() > 0) {
//    			List<AccessRights> listAc = new LinkedList<>();
//    			for(AccessRightDTO _dto : accessRights) {
//    				AccessRights data = _dto.getData();
//    				data.setOwner(ug);
//    				listAc.add(data);
//    			}
//    			ug.setAccessRights(listAc);
//    		}
//    		return ug;
//    }
//    
//    /**
//     * @return the m_systemid
//     */
//    public Integer getSystemId ()
//    {
//        return systemId;
//    }
//
//    /**
//     * @param m_systemid the m_systemid to set
//     */
//    public void setSystemId (Integer systemid)
//    {
//        this.systemId = systemid;
//    }
//
//    /**
//     * @return the m_name
//     */
//    public String getName ()
//    {
//        return name;
//    }
//
//    /**
//     * @param m_name the m_name to set
//     */
//    public void setName (String name)
//    {
//        this.name = name;
//    }
//
//    /**
//     * @return the m_memo
//     */
//    public String getMemo ()
//    {
//        return memo;
//    }
//
//    /**
//     * @param m_memo the m_memo to set
//     */
//    public void setMemo (String memo)
//    {
//        this.memo = memo;
//    }
//    
//    public void setAccessRights (List<AccessRightDTO> _listAR)
//    {
//        accessRights = _listAR;
//    }
//    
//    public List<AccessRightDTO> getAccessRights ()
//    {
//        return accessRights;
//    }   
//}
