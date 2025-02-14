select *
from nodeinstancelog
where 1 = 1
and processinstanceid = 1
order by id, nodeinstanceid;

select *
from processinstanceinfo;

select *
from qrtz_simple_triggers;

select *
from qrtz_triggers;