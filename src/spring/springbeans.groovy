
beans = {
	xmlns context:"http://www.springframework.org/schema/context"
	context."annotation-config"()
	
	
	xmlns task:"http://www.springframework.org/schema/task"
	task."scheduler"("id":"scheduler","pool-size":"10")
	task."executor"("id":"executor","pool-size":"10")
	task."annotation-driven"("scheduler":"scheduler", "executor":"executor")
   
	context."component-scan"("annotation-config":"true", "base-package":"timetracker")
	dataRetrieverTask(timetracker.DataRetrieverService)
 
}