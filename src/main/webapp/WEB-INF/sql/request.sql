#sql("findList")
      SELECT r_id rid,name,m_id mid FROM request
        #for(x:cond)
           #(for.index == 0 ? "where": "and") #(x.key) #para(x.value)
        #end
#end

#sql("findByRid")
      SELECT r_id rid,name,tel,remark FROM request
        #for(x:cond)
           #(for.index == 0 ? "where": "and") #(x.key) #para(x.value)
        #end
#end


#sql("findListByUidAndStatus")
      SELECT name,m_id mid, status FROM request
        #for(x:cond)
           #(for.index == 0 ? "where": "and") #(x.key) #para(x.value)
        #end
#end