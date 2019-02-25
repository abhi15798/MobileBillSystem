package com.cg.billing.daoservices;
import java.util.List;
import com.cg.billing.beans.PostpaidAccount;
public interface PostpaidAccountDao {
	PostpaidAccount save(PostpaidAccount postpaidAccount);
	boolean update(PostpaidAccount postpaidAccount);
	PostpaidAccount findOne(long mobileNo);
	List<PostpaidAccount>findAll(int customerID);
	boolean remove(PostpaidAccount postpaidAccount,long mobileNo);
}
