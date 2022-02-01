# Scooby-Doo Application

**Description**

An app aimed at solving crimes in Scooby-Doo. The system allows you to analyze case data, calculate the criminal by evidence and testimony, form a criminal case to the police and select effective traps for monsters, making an order to buy certain things.

**Business Processes**

Detectives are registered in the system (Velma, Daphne, Shaggy, Fred). Data on their equipment is filled in (quantity, quality, type of weapon, predisposition of the monster) and their bank accounts are created. When a monster is attacked, witnesses have access to the registration of the monster's actions (crime scene: coordinates, place, time, method of attack, signs) and then the system notifies detectives about the attack. Detectives go to the scene of the incident, interview the victim, according to his testimony, a statement about the crime is drawn up and an initial list of suspects is compiled. After that, the team is divided: one part interviews witnesses and suspects - data on suspects are supplemented (motives, alibis, new people). The second part of the team searches the crime scene, searches and registers evidence. The last Velma together with the others record data about the monster (description of the monster, its characteristics).

Depending on the type of monster, its features, the usefulness of the trap is generated and the most suitable one is selected:
- The monster has something piercing or cutting - a pit or a cage
- Monster flying: cage, mesh, canvas
- The monster uses something explosive or shooting as a weapon - a pit
- Underwater monster: a ship with a net or a crane (depending on the weight and the presence of sharp objects)
- The monster has no features (Ghost, zombie) - a net or a canvas

The team confirms the choice of the trap and the system makes an order for the purchase of the necessary tools for the organization of the trap, withdrawing money from the accounts of those involved in the case.
Further, according to the equipment of the team members, a role in the capture is generated: the bait, or responsible for the trap, or supervising the activation of the trap.

The system also analyzes the evidence, alibis of suspects and displays a list of perpetrators, their involvement in the case and alleged motives.
Detectives go out to catch the monster, enter information about the departure into the system. If the monster was not caught, then the usefulness of this equipment and the selected type of trap will be lowered, which affects the subsequent generation of the plan. The team goes back to search for evidence and makes changes to the list of suspects.
If the monster is caught, the system collects all the data on the investigation, punishments are imposed, analyzing the damage and reward for disclosure, and everything is entered into the final case, it becomes available to the sheriff. He studies the case, confirms the sentence imposed and closes the case: if the sheriff agrees with the motives and punishment, the entire fee goes to bank accounts, divided among the participants in the investigation, otherwise half of the fee.

**Tools**
- Spring Boot, Spring Security, Spring Web, Spring JPA
- Freemarker, BootStrap
- PostgreSQL
- Hibernate

**View of App**

_Authentication and authorization of users with different fields_

![registration](/img/Registration.png)

_Role management (Investigator and Admin)_

![roles](/img/Investigators.png)

_Information about crimes_

![crime](/img/Crime.png)

_Information about profile_

![profile](/img/Profile.png)

_Information about characters_

![charecters](/img/Characters.png)
