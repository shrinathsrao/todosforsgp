Feature: Feature to test Todo List Page

  #Please ensure that the comma-separated values do not have appending or following spaces before and after commas
  @additems
  Scenario Outline: Validate that User is able to add items to the to-do list
    Given Web <browser> is Open
    And User is on To-do list page
    When User enters <values> in To-do list text box
    Then User is able to see the entered <values> in the To-Do list

    Examples: 
      | browser | values                                               |
      | chrome  | Prepare Coffee,Go to Work,Wrap Up work,Sleep on Time |
      | edge    | Meet Vishal,Submit Assignments,Play Chess            |

  @edititems
  Scenario Outline: Validate that User is able to edit the added items to the to-do list
    Given Web <browser> is Open
    And User is on To-do list page
    And User enters <values> in To-do list text box
    When User edits the existing <values> with <new values>
    Then User is able to see the entered <new values> in the To-Do list

    Examples: 
      | browser | values                     | new values                               |
      | chrome  | Play Cricket,Play football | Do not play Cricket,Do not Play Football |
      | edge    | Play Cricket,Play football | Do not play Cricket,Do not Play Football |

  @deleteitems
  Scenario Outline: Validate that User is able to delete the added item from the to-do list
    Given Web <browser> is Open
    And User is on To-do list page
    And User enters <values> in To-do list text box
    When User deletes an entry from existing <values>
    Then User is not able to see the deleted entry <values> in the To-Do list

    Examples: 
      | browser | values       |
      | chrome  | Play Cricket |
      | edge    | Play Cricket |

  @checkitems
  Scenario Outline: Validate that User is able to check the added items as completed on the to-do list
    Given Web <browser> is Open
    And User is on To-do list page
    And User enters <values> in To-do list text box
    When User marks the existing <completed values> as completed from list of <values>
    Then <completed values> value out of all the <values> is shown as completed
    And items left counter reflects the changes caused by <completed values> items out of all <values>

    Examples: 
      | browser | values                                 | completed values                       |
      | chrome  | Play Cricket,Play Football,Play Hockey | Play Cricket,Play Football,Play Hockey |
      | edge    | Play Cricket,Play Football,Play Hockey | Play Football                          |

  @clearcompleteditems
  Scenario Outline: Validate that User is able to clear all the completed items on the to-do list
    Given Web <browser> is Open
    And User is on To-do list page
    And User enters <values> in To-do list text box
    And User marks the existing <completed values> as completed from list of <values>
    When User clicks on Clear completed button
    Then User is not able to see the deleted entry <completed values> in the To-Do list

    Examples: 
      | browser | values                                 | completed values         |
      | chrome  | Play Cricket,Play Football,Play Hockey | Play Cricket,Play Hockey |
      | edge    | Play Cricket,Play Football,Play Hockey | Play Football            |

  @filterchecks
  Scenario Outline: Validate that User is able to view the appropriate values using filters on landing page of to-do list
    Given Web <browser> is Open
    And User is on To-do list page
    And User enters <values> in To-do list text box
    And User marks the existing <completed values> as completed from list of <values>
    When User clicks on <filter> button
    Then User only sees the <filter> set of <values> and <completed values> in the to-do list

    Examples: 
      | browser | values                                 | completed values         | filter    |
      | chrome  | Play Cricket,Play Football,Play Hockey | Play Cricket,Play Hockey | Completed |
      | edge    | Play Cricket,Play Football,Play Hockey | Play Football            | Active    |
