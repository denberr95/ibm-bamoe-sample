select
	a.id, 
	a.log_date,
	a.nodecontainerid, 
	a.nodename, 
	a.nodetype, 
	a.type
from nodeinstancelog a
where 1 = 1
and a.processinstanceid = 1
order by a.id, a.nodeinstanceid;

select *
from processinstanceinfo;

select *
from qrtz_simple_triggers;

select *
from qrtz_triggers;