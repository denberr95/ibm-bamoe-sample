select *
from nodeinstancelog
where 1 = 1
and processinstanceid = 1
order by id, nodeinstanceid;

select *
from nodeinstancelog
where 1 = 1
and processinstanceid = 1
order by id, nodeinstanceid;

select *
from processinstanceinfo;

select *
from qrtz_triggers;

select *
from qrtz_simple_triggers;

-- ErrorInfo 
select errorinfo0_.id as col_0_0_ 
from ErrorInfo errorinfo0_
where errorinfo0_.timestamp <= ?

delete from ErrorInfo 
where id in (?, ?);

-- RequestInfo
select requestinf0_.id as col_0_0_ 
from RequestInfo requestinf0_ 
where requestinf0_.status in ('ERROR', 'CANCELLED', 'DONE')
and requestinf0_.timestamp <= ?;

delete from RequestInfo
where id in (?, ?);

-- AuditTaskImpl
select audittaski0_.id as col_0_0_ 
from AuditTaskImpl audittaski0_ 
where audittaski0_.processInstanceId in (
    select processins1_.processInstanceId
    from ProcessInstanceLog processins1_
    where processins1_.status in (2, 3)
);

delete from AuditTaskImpl
where id in (?, ?);

-- TaskEvent
select taskeventi0_.id as col_0_0_
from TaskEvent taskeventi0_ 
where taskeventi0_.createdOn <= ?
and taskeventi0_.processInstanceId in (
    select processins1_.processInstanceId 
    from ProcessInstanceLog processins1_ 
    where processins1_.status in (2, 3)
);

delete from TaskEvent 
where id in (?, ?);

-- TaskVariableImpl
select taskvariab0_.id as col_0_0_
from TaskVariableImpl taskvariab0_ 
where taskvariab0_.modificationDate <= ?
and taskvariab0_.processInstanceId in (
    select processins1_.processInstanceId 
    from ProcessInstanceLog processins1_ 
    where processins1_.status in (2, 3)
);

delete from TaskVariableImpl 
where id in (?, ?);

-- NodeInstanceLog
select nodeinstan0_.id as col_0_0_ 
from NodeInstanceLog nodeinstan0_ 
where nodeinstan0_.log_date <= ?
and nodeinstan0_.processInstanceId in (
    select processins1_.processInstanceId 
    from ProcessInstanceLog processins1_ 
    where processins1_.status in (2, 3)
);

delete from NodeInstanceLog 
where id in (?, ?);

-- VariableInstanceLog
select variablein0_.id as col_0_0_ 
from VariableInstanceLog variablein0_ 
where variablein0_.log_date <= ? 
and variablein0_.processInstanceId in (
    select processins1_.processInstanceId 
    from ProcessInstanceLog processins1_ 
    where processins1_.status in (2, 3)
);

delete from VariableInstanceLog 
where id in (?, ?);

-- ProcessInstanceLog
select processins0_.id as col_0_0_ 
from ProcessInstanceLog processins0_ 
where processins0_.status in (2, 3)
and processins0_.end_date <= ?;

delete from ProcessInstanceLog 
where id in (?, ?);
