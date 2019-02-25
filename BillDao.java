package com.cg.billing.daoservices;
import java.util.List;
import com.cg.billing.beans.Bill;
public interface BillDao {
	Bill save(Bill bill);
	Bill findOne(int billID);
	List<Bill> findAll();
}
