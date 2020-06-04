/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ProdCategoryListDTO.java
 *
 * Created on Oct 18, 2017, 3:27:27 PM
 */

package sunwell.permaisuri.bus.dto.sales;

import java.util.LinkedList;



import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import sunwell.permaisuri.bus.dto.StandardDTO;
//import sunwell.permaisuri.core.entity.cred.UserGroup;
import sunwell.permaisuri.core.entity.inventory.ItemCategory;
import sunwell.permaisuri.core.entity.sales.CartDetail;
import sunwell.permaisuri.core.entity.sales.SalesOrder;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CartDetailListDTO extends StandardDTO
{
    
    private List<CartDetailDTO> listCartDetail;
    
    public CartDetailListDTO() {
    }
    
    public CartDetailListDTO(List<CartDetail> _detail) {
        setData (_detail);
    }
    
    public void setData(List<CartDetail> _details) {
        if(_details != null && _details.size () > 0) {
        	listCartDetail = new LinkedList<> ();
            for (CartDetail cd : _details) {
                listCartDetail.add (new CartDetailDTO (cd));
            }
        }
        else
            listCartDetail = null;
    }

    /**
     * @return the listUser
     */
    public List<CartDetailDTO> getListSalesOrder ()
    {
        return listCartDetail;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListUserGroup (List<CartDetailDTO> _list)
    {
        this.listCartDetail = _list;
    }
}
