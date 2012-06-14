package org.agoncal.application.petstore.tomee;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import javax.management.Description;
import javax.management.MBean;
import javax.management.ManagedAttribute;
import javax.management.ManagedOperation;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.openmbean.TabularData;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;

@MBean
@Description("Order info")
@ApplicationScoped
public class OrderMBean {
    private final Map<String, AtomicInteger> orderByCustomer = new ConcurrentHashMap<String, AtomicInteger>();
    private AtomicInteger orderNumber = new AtomicInteger();

    @ManagedAttribute
    @Description("number of order since the instance is up")
    public int getOrderNumber() {
        return orderNumber.get();
    }

    @ManagedAttribute
    @Description("number of order by customer since the instance is up")
    public TabularData getStats() {
        final Properties properties = new Properties();
        for (Map.Entry<String, AtomicInteger> entry : orderByCustomer.entrySet()) {
            properties.setProperty(entry.getKey(), Integer.toString(entry.getValue().get()));
        }
        return tabularData("order/customer", "Order by customer", properties);
    }

    @ManagedOperation
    @Description("reset")
    public void reset() {
        orderNumber.set(0);
        orderByCustomer.clear();
    }

    public void incr(final String fullname) {
        if (!orderByCustomer.containsKey(fullname)) {
            synchronized (orderByCustomer) {
                if (!orderByCustomer.containsKey(fullname)) {
                    orderByCustomer.put(fullname, new AtomicInteger());
                }
            }
        }
        orderByCustomer.get(fullname).incrementAndGet();
        orderNumber.incrementAndGet();
    }

    private static TabularData tabularData(String typeName, String typeDescription, Properties properties) {
        String[] names = properties.keySet().toArray(new String[properties.size()]);
        Object[] values = new Object[names.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = properties.get(names[i]).toString(); // hibernate put objects in properties for instance
        }
        return tabularData(typeName, typeDescription, names, values);
    }

    private static TabularData tabularData(String typeName, String typeDescription, String[] names, Object[] values) {
        if (names.length == 0) {
            return null;
        }

        OpenType<?>[] types = new OpenType<?>[names.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = SimpleType.STRING;
        }

        try {
            CompositeType ct = new CompositeType(typeName, typeDescription, names, names, types);
            TabularType type = new TabularType(typeName, typeDescription, ct, names);
            TabularDataSupport data = new TabularDataSupport(type);

            CompositeData line = new CompositeDataSupport(ct, names, values);
            data.put(line);

            return data;
        } catch (OpenDataException e) {
            return null;
        }
    }
}
