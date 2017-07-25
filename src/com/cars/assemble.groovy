package com.cars

class Assemble implements Serializable{

    Assemble() {}

    def assembleReleaseBuildWithTask(task){
      println ("Assemble")
    }

    def assembleBuildsWithNames(flavours, variant){
    }

    def assembleBuilds(flavours, variant = "Release"){
        if(flavours == "All"){
            assembleReleaseBuildWithTask("AssembleAll${variant}")
        }else{
            assembleBuildsWithNames(flavours,variant)
        }
    }

    def archiveResults(){
    }

}
