package com.example.decision_engine.model;

//for rule evaluation
/*🧠 Important
This is NOT an Entity
This is runtime input data
Comes from API request body*/

// i need this because my SpEl expression reference this #candidate.experience>=5?20:5;
//So Spring must know: //What is candidate? //What fields does it have?
public class Candidate {

    private int experience;
    private int skillCount;
    private String location;
    private int expectedSalary;

//    getters and setters
     public int getExperience(){                  //use in SpEl expression
         return experience;
     }

//for dynamic like
/*
#candidate.skillCount >= 3 ? 15 : 0
#candidate.location == 'India' ? 10 : 0
#candidate.expectedSalary < 1000000 ? 20 : 0
*/
    public int getSkillCount(){
        return skillCount;
    }

    public String getLocation() {
        return location;
    }

    public int getExpectedSalary() {
        return expectedSalary;
    }
}
