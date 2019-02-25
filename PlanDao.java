package com.cg.billing.daoservices;
import java.util.List;
import com.cg.billing.beans.Plan;
public interface PlanDao {
  Plan findOne(int planId);
  List<Plan>findAll();
}
