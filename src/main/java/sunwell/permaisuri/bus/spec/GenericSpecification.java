package sunwell.permaisuri.bus.spec;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import sunwell.permaisuri.bus.service.Filters;
import sunwell.permaisuri.bus.service.Filters.Filter;
import sunwell.permaisuri.bus.service.Filters.Unknown;
import sunwell.permaisuri.bus.util.Util;
import sunwell.permaisuri.core.entity.customer.Customer;
import sunwell.permaisuri.core.entity.sales.SalesInvoice;
import sunwell.permaisuri.core.entity.sales.SalesOrder;

public class GenericSpecification<T> implements Specification<T>
{
	protected Filters filters ;
	protected Class<T> clazz;
	protected Predicate predicate ;
	protected List<String> blackListedAttributes = defaultBlackListedAttributes;
	
	private static final List<String> defaultBlackListedAttributes =
			Arrays.asList("sysCreator", "sysUpdater", "sysCreateDate", "sysLastUpdateDate");
	
	public GenericSpecification(Filters _filters, Class<T> _class) {
		filters = _filters;
		clazz = _class;
	}
	
//	public GenericSpecification(List<String> _filters, Class<T> _class) {
//		filters = _filters;
//		clazz = _class;
//	}

	@Override
	public Predicate toPredicate (Root<T> _root, CriteriaQuery<?> _cq, CriteriaBuilder _cb)
	{
		try {
			if(filters == null ||  filters.getFilters() == null || filters.getFilters().size() <= 0)
				return null;
						
			return convertFiltersToPredicate(_root, _cq, _cb);
		}
		catch(Exception _e) {
			_e.printStackTrace();
			throw new RuntimeException(_e);
		}
	}
	
//	public boolean isError() {
//		return predicate == null ;
//	}
	
	protected Predicate convertFiltersToPredicate(Root<T> _root, CriteriaQuery<?> _cq, CriteriaBuilder _cb) throws Exception {
		if(filters == null ||  filters.getFilters() == null || filters.getFilters().size() <= 0)
			return null;
		
		Predicate retval = null;
		List<Predicate> predicates = new LinkedList<>();
		for(Filter f : filters.getFilters()) {
			retval = convertFilterToPredicate(f, _root, _cq, _cb);
			if(retval != null)
				predicates.add(retval);
//			String type = Util.findType(_clazz, f.getKey());
//			switch(f.getComparison()) {
//				case Filters.COMPARISON_EQUAL : 
//					switch(type) {
//						case Util.TYPE_CALENDAR :
//							retval = _cb.equal(_root.<Calendar>get(f.getKey()), (Calendar)f.getValue());
//							break;
//						case Util.TYPE_INTEGER:
//						case Util.TYPE_INT:
//							retval = _cb.equal(_root.<Integer>get(f.getKey()), (Integer)f.getValue());
//							break;
//						case Util.TYPE_DB:
//						case Util.TYPE_DOUBLE:
//							retval = _cb.equal(_root.<Double>get(f.getKey()), (Double)f.getValue());
//							break;
//					}
//					break;
//				case Filters.COMPARISON_GREATER_THAN : 
//					switch(type) {
//						case Util.TYPE_CALENDAR :
//							retval = _cb.greaterThan(_root.<Calendar>get(f.getKey()), (Calendar)f.getValue());
//							break;
//						case Util.TYPE_INTEGER:
//						case Util.TYPE_INT:
//							retval = _cb.greaterThan(_root.<Integer>get(f.getKey()), (Integer)f.getValue());
//							break;
//						case Util.TYPE_DB:
//						case Util.TYPE_DOUBLE:
//							retval = _cb.greaterThan(_root.<Double>get(f.getKey()), (Double)f.getValue());
//							break;
//					}
//					break;
//				case Filters.COMPARISON_LESS_THAN : 
//					switch(type) {
//						case Util.TYPE_CALENDAR :
//							retval = _cb.lessThan(_root.<Calendar>get(f.getKey()), (Calendar)f.getValue());
//							break;
//						case Util.TYPE_INTEGER:
//						case Util.TYPE_INT:
//							retval = _cb.lessThan(_root.<Integer>get(f.getKey()), (Integer)f.getValue());
//							break;
//						case Util.TYPE_DB:
//						case Util.TYPE_DOUBLE:
//							retval = _cb.lessThan(_root.<Double>get(f.getKey()), (Double)f.getValue());
//							break;
//					}
//					break;
//				case Filters.COMPARISON_EQUAL_GREATER_THAN : 
//					switch(type) {
//						case Util.TYPE_CALENDAR :
//							retval = _cb.greaterThanOrEqualTo(_root.<Calendar>get(f.getKey()), (Calendar)f.getValue());
//							break;
//						case Util.TYPE_INTEGER:
//						case Util.TYPE_INT:
//							retval = _cb.greaterThanOrEqualTo(_root.<Integer>get(f.getKey()), (Integer)f.getValue());
//							break;
//						case Util.TYPE_DB:
//						case Util.TYPE_DOUBLE:
//							retval = _cb.greaterThanOrEqualTo(_root.<Double>get(f.getKey()), (Double)f.getValue());
//							break;
//					}
//					break;
//				case Filters.COMPARISON_LIKE : 
//					switch(type) {
//						case Util.TYPE_STRING :
//							retval = _cb.like(_root.<String>get(f.getKey()), (String)f.getValue());
//							break;
//						default:
//							throw new Exception("Error, invalid type: " + type + " for like comparison");
//					}
//					break;
//			}
		}
		
		if(predicates.size() > 1) {
			retval = _cb.and(predicates.toArray(new Predicate[0]));
		}
		
		return retval;
	}
	
	private Path<EntityManager> messyMethod() {
		return null;
	}
		
	
	@SuppressWarnings("unchecked")
	protected Predicate convertFilterToPredicate(Filter _f, Path _root, CriteriaQuery<?> _cq, CriteriaBuilder _cb) throws Exception {
		String type = null;
		Path path = _root;
		String key = _f.getKey();
		String value = _f.getValue();
		
//		if(1==1) {
//			System.out.println("TEST LIKE");
//			return _cb.like(path.get("customer").<String>get("firstName"), "%" + "John" + "%");
//		}
		
		System.out.println("KEY: " + key + " value: " + value);
		
		type = Util.findType(clazz, key);
		System.out.println("TYPE: " + type);
		
		if (_f.getKey().contains("_")) {
			System.out.println("LOOKING FOR NESTED ATTRIBUTES");
			String[] nestedAttributes = _f.getKey().split("_");
			if(nestedAttributes.length < 2)
				throw new Exception("Unknown filter: " + _f.getKey());
			for(int i = 0 ; i < nestedAttributes.length - 1 ; i++) {
				System.out.println("NESTED: " + i + " NAME: " + nestedAttributes[i]);
				path = path.get(nestedAttributes[i]);
			}
			key = nestedAttributes[nestedAttributes.length - 1];
//			value = ((Filters.Nested) _f.getValue()).getValue();
		}
//		else if(_f.getValue() instanceof Filters.Unknown) {
//			System.out.println("UNKNOWN FILTER");
//			return convertUnknownFilterToPredicate(_f, _root, _cq, _cb);
//		}
		
//		if(1==1)
//			return _cb.like(_root.get("customer").get("firstName"), "%" + "john" + "%");
		
		switch(_f.getComparison()) {
		case Filters.COMPARISON_EQUAL : 
			Object retval ;
			System.out.println(" IS EQ: " + Util.TYPE_STRING.equals(type));
			switch(type) {
				case Util.TYPE_CALENDAR : 
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					retval = Calendar.getInstance();
					((Calendar)retval).setTime(sdf.parse((String)value));
					break;
				case Util.TYPE_BL:
				case Util.TYPE_BOOLEAN: 
					retval = Boolean.parseBoolean(value);
					break;
				case Util.TYPE_INTEGER:
				case Util.TYPE_INT: 
					retval = new Integer(value);
					break;
				case Util.TYPE_DB:
				case Util.TYPE_DOUBLE: 
					retval = new Double(value);
					break;
				case Util.TYPE_STRING:
					retval = value;
					break;
				default : 
					throw new Exception("Error, invalid type for equality comparison: " + type);
			}
			return _cb.equal(path.get(key), retval);

		case Filters.COMPARISON_GREATER_THAN : 
			switch(type) {
				case Util.TYPE_CALENDAR : {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Calendar val = Calendar.getInstance();
					val.setTime(sdf.parse((String)value));
					return _cb.greaterThan(path.get(key), val);
				}
				case Util.TYPE_INTEGER:
				case Util.TYPE_INT: {
					Integer val = new Integer(value);
					return _cb.greaterThan(path.get(key), val);
				}
				case Util.TYPE_DB:
				case Util.TYPE_DOUBLE: {
					Double val = new Double(value);
					return _cb.greaterThan(path.get(key), val);
				}
				default : 
					throw new Exception("Error, invalid type for greter than comparison: " + type);
			}
		case Filters.COMPARISON_LESS_THAN : 
			switch(type) {
				case Util.TYPE_CALENDAR : {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Calendar val = Calendar.getInstance();
					val.setTime(sdf.parse((String)value));
					return _cb.lessThan(path.get(key), val);
				}
				case Util.TYPE_INTEGER:
				case Util.TYPE_INT: {
					Integer val = new Integer(value);
					return _cb.lessThan(path.get(key), val);
				}
				case Util.TYPE_DB:
				case Util.TYPE_DOUBLE: {
					Double val = new Double(value);
					return _cb.lessThan(path.get(key), val);
				}
				default : 
					throw new Exception("Error, invalid type for less than comparison: " + type);
			}
		case Filters.COMPARISON_EQUAL_GREATER_THAN : 
			switch(type) {
				case Util.TYPE_CALENDAR : {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Calendar val = Calendar.getInstance();
					val.setTime(sdf.parse((String)value));
					return _cb.greaterThanOrEqualTo(path.get(key), val);
				}
				case Util.TYPE_INTEGER:
				case Util.TYPE_INT: {
					Integer val = new Integer(value);
					return _cb.greaterThanOrEqualTo(path.get(key), val);
				}
				case Util.TYPE_DB:
				case Util.TYPE_DOUBLE: {
					Double val = new Double(value);
					return _cb.greaterThanOrEqualTo(path.get(key), val);
				}
				default : 
					throw new Exception("Error, invalid type for greter than or equal to comparison: " + type);
			}
		case Filters.COMPARISON_LIKE : 
			switch(type) {
				case Util.TYPE_STRING :
//					System.out.println("STRING TYPE IS SWITCHED, KEY: " + key + " VALUE: " + value);
					return _cb.like(path.<String>get(key), "%" + (String)value + "%");
				default:
					throw new Exception("Error, invalid type: " + type + " for like comparison");
			}
		default : 
			throw new Exception("Error, no comparison operator found for operator: " + _f.getComparison());
}
		
		
		
//		switch(_f.getComparison()) {
//			case Filters.COMPARISON_EQUAL : 
//				switch(type) {
//					case Util.TYPE_CALENDAR :
//						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//						Calendar cal = Calendar.getInstance();
//						cal.setTime(sdf.parse((String)value));
//						return _cb.equal(path.get(key), cal);
//					case Util.TYPE_INTEGER:
//					case Util.TYPE_INT:
//						return _cb.equal(path.get(key), (Integer)value);
//					case Util.TYPE_DB:
//					case Util.TYPE_DOUBLE:
//						return _cb.equal(path.get(key), (Double)value);
//					default : 
//						throw new Exception("Error, invalid type for equality comparison: " + type);
//				}
//			case Filters.COMPARISON_GREATER_THAN : 
//				switch(type) {
//					case Util.TYPE_CALENDAR :
//						System.out.println("Calendar is switched");
//						return _cb.greaterThan(path.get(key), (Calendar)value);
//					case Util.TYPE_INTEGER:
//					case Util.TYPE_INT:
//						System.out.println("Integer is switched");
//						return _cb.greaterThan(path.get(key), (Integer)value);
//					case Util.TYPE_DB:
//					case Util.TYPE_DOUBLE:
//						return _cb.greaterThan(path.get(key), (Double)value);
//					default : 
//						throw new Exception("Error, invalid type for greter than comparison: " + type);
//				}
//			case Filters.COMPARISON_LESS_THAN : 
//				switch(type) {
//					case Util.TYPE_CALENDAR :
//						return _cb.lessThan(path.get(key), (Calendar)value);
//					case Util.TYPE_INTEGER:
//					case Util.TYPE_INT:
//						return _cb.lessThan(path.get(key), (Integer)value);
//					case Util.TYPE_DB:
//					case Util.TYPE_DOUBLE:
//						return _cb.lessThan(path.get(key), (Double)value);
//					default : 
//						throw new Exception("Error, invalid type for less than comparison: " + type);
//				}
//			case Filters.COMPARISON_EQUAL_GREATER_THAN : 
//				switch(type) {
//					case Util.TYPE_CALENDAR :
//						return _cb.greaterThanOrEqualTo(path.get(key), (Calendar)value);
//					case Util.TYPE_INTEGER:
//					case Util.TYPE_INT:
//						return _cb.greaterThanOrEqualTo(path.get(key), (Integer)value);
//					case Util.TYPE_DB:
//					case Util.TYPE_DOUBLE:
//						return _cb.greaterThanOrEqualTo(path.get(key), (Double)value);
//					default : 
//						throw new Exception("Error, invalid type for greter than or equal to comparison: " + type);
//				}
//			case Filters.COMPARISON_LIKE : 
//				switch(type) {
//					case Util.TYPE_STRING :
//						return _cb.like(path.get(key), (String)value);
//					default:
//						throw new Exception("Error, invalid type: " + type + " for like comparison");
//				}
//			default : 
//				throw new Exception("Error, no comparison operator found for operator: " + _f.getComparison());
//	}
		
//		switch(_f.getComparison()) {
//			case Filters.COMPARISON_EQUAL : 
//				switch(type) {
//					case Util.TYPE_CALENDAR :
//						return _cb.equal(_root.<Calendar>get(_f.getKey()), (Calendar)_f.getValue());
//					case Util.TYPE_INTEGER:
//					case Util.TYPE_INT:
//						return _cb.equal(_root.<Integer>get(_f.getKey()), (Integer)_f.getValue());
//					case Util.TYPE_DB:
//					case Util.TYPE_DOUBLE:
//						return _cb.equal(_root.<Double>get(_f.getKey()), (Double)_f.getValue());
//					default : 
//						throw new Exception("Error, invalid type for equality comparison: " + type);
//				}
//			case Filters.COMPARISON_GREATER_THAN : 
//				switch(type) {
//					case Util.TYPE_CALENDAR :
//						return _cb.greaterThan(_root.get(_f.getKey()), (Calendar)_f.getValue());
//					case Util.TYPE_INTEGER:
//					case Util.TYPE_INT:
//						System.out.println("Integer is switched");
//						return _cb.greaterThan(_root.get(_f.getKey()), (Integer)_f.getValue());
//					case Util.TYPE_DB:
//					case Util.TYPE_DOUBLE:
//						return _cb.greaterThan(_root.<Double>get(_f.getKey()), (Double)_f.getValue());
//					default : 
//						throw new Exception("Error, invalid type for greter than comparison: " + type);
//				}
//			case Filters.COMPARISON_LESS_THAN : 
//				switch(type) {
//					case Util.TYPE_CALENDAR :
//						return _cb.lessThan(_root.<Calendar>get(_f.getKey()), (Calendar)_f.getValue());
//					case Util.TYPE_INTEGER:
//					case Util.TYPE_INT:
//						return _cb.lessThan(_root.<Integer>get(_f.getKey()), (Integer)_f.getValue());
//					case Util.TYPE_DB:
//					case Util.TYPE_DOUBLE:
//						return _cb.lessThan(_root.<Double>get(_f.getKey()), (Double)_f.getValue());
//					default : 
//						throw new Exception("Error, invalid type for less than comparison: " + type);
//				}
//			case Filters.COMPARISON_EQUAL_GREATER_THAN : 
//				switch(type) {
//					case Util.TYPE_CALENDAR :
//						return _cb.greaterThanOrEqualTo(_root.<Calendar>get(_f.getKey()), (Calendar)_f.getValue());
//					case Util.TYPE_INTEGER:
//					case Util.TYPE_INT:
//						return _cb.greaterThanOrEqualTo(_root.<Integer>get(_f.getKey()), (Integer)_f.getValue());
//					case Util.TYPE_DB:
//					case Util.TYPE_DOUBLE:
//						return _cb.greaterThanOrEqualTo(_root.<Double>get(_f.getKey()), (Double)_f.getValue());
//					default : 
//						throw new Exception("Error, invalid type for greter than or equal to comparison: " + type);
//				}
//			case Filters.COMPARISON_LIKE : 
//				switch(type) {
//					case Util.TYPE_STRING :
//						return _cb.like(_root.<String>get(_f.getKey()), (String)_f.getValue());
//					default:
//						throw new Exception("Error, invalid type: " + type + " for like comparison");
//				}
//			default : 
//				throw new Exception("Error, no comparison operator found for operator: " + _f.getComparison());
//		}
	}
	
	protected Predicate convertUnknownFilterToPredicate(Filter _f, Path _root, CriteriaQuery<?> _cq, CriteriaBuilder _cb) throws Exception {
		throw new Exception("Unknown filter: " + _f.getKey());
	}
	
//	protected Predicate convertNestedFilterToPredicate(Filter _f, Root<T> _root, CriteriaBuilder _cb, Class<T> _clazz) throws Exception {
//		String key = _f.getKey();
//		String[] attrNames = key.split("_");
//		
//	}
	
//	public Filters convertToFilters(List<String> _params, Class _class) throws Exception {
//		if(_params == null || _params.size() <= 0)
//			return null;
//		
//		Filters filters = new Filters();
//		for(String _p : _params) {
//			System.out.println("line: " + _p);
//			String op = findOperator(_p);
//			
//			if(op == null)
//				throw new Exception("Can't find the operator: " + op);
//			
//			String[] line = _p.split(op);
//			if(line.length != 2)
//				throw new Exception("Wrong criteria format");
//			
//			String typeName = null;
//			Object value = null;
//			
//			typeName = Util.findType(_class, line[0]);
//			
//			switch(typeName) {
//				case Util.TYPE_CALENDAR :
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//					Calendar cal = Calendar.getInstance();
//					cal.setTime(sdf.parse(line[1]));
//					value = cal;
//					break;
//				case Util.TYPE_INTEGER :
//				case Util.TYPE_INT :
//					value = new Integer(line[1]);
//					break;
//				case Util.TYPE_DB :
//				case Util.TYPE_DOUBLE :
//					value = new Double(line[1]);
//					break;
//				case Util.TYPE_STRING :
//					value = line[1];
//					break;
//			}
//			
//			Filter f = new Filter(line[0], SYMBOL_MAP.get(op), value);
//			filters.addFilter(f);
//		}
//		
//		return filters;
//	}

}
