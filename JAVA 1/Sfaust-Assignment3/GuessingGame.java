/*
* Name:        Scott Faust
* Class:       22_WI_INFO_1521_WW
* Assignment:  SfaustAssignment3.java
* Date:        12/16/2022
* Resources: SOOOOOO many resources, this had me perplexed for a while, mostly at the shear number 
of outcomes that are possible given the paramiters of the assignment. I went through 4 or 5 
different itterations before landing on this as its the last day to get it done but I'm happy with 
it now. 
I used the following resources to help me with this assignment:
https://www.w3schools.com/java java documentation and examples
the class lectures and notes
Google for help finding animals that fit the paramiters
I used the department of transportation vin lookup API as I was going to utilize that at one point 
to get the peramiters to make an API call but for the number of options I thought were nessissary
 it wasnt going to work out.
* Description: This program will play a game of twenty questions with the user.
*/

import java.util.Scanner;


public class GuessingGame 
{

    static String stringInput(String questionPrompt) 
    {
        Scanner input = new Scanner(System.in);
        System.out.println(questionPrompt);
        String stringResponse = input.nextLine();
        return stringResponse;
    }

    static int intInput(String questionPrompt) 
    {
        Scanner input = new Scanner(System.in);
        System.out.println(questionPrompt);
        int intResponse = input.nextInt();
        return intResponse;
    }

    static String getClimate()
    {
        String climate = "";
        do 
        {
            switch (stringInput("What climate is this animal native to?")) 
            {
                case "tropical":
                case "Tropical":
                case "TROPICAL":
                    climate = "tropical";
                    break;
                case "temperate":
                case "Temperate":
                case "TEMPERATE":
                    climate = "temperate";
                    break;
                case "polar":
                case "Polar":
                case "POLAR":
                    climate = "polar";
                    break;
                default:
                    System.out.println("Please enter a valid response (tropical, temperate, or polar)");
                    break;
            }

        } while (climate == "");
        return climate;
    }

    static int getLegCount()
    {
        int legCount = -1;
        do 
        {
            switch (intInput("How many legs does this animal have?")) 
            {
                case 0:
                    legCount = 0;
                    break;
                case 2:
                    legCount = 2;
                    break;
                case 4:
                    legCount = 4;
                    break;
                case 8:
                    legCount = 8;
                    break;
                default:
                    System.out.println("Please enter a valid response\n2\n4\n6\n8\n");
                    break;
            }
        } while (legCount == -1);
        return legCount;

    }

    static boolean getTailPresent()
    {
        boolean tailPresent = false;
        boolean tailQuestonRelease = false;
        do 
        {
            switch (stringInput("Does this animal have a tail?")) 
            {
                case "yes":
                case "Yes":
                case "YES":
                    tailPresent = true;
                    tailQuestonRelease = true;
                    break;
                case "no":
                case "No":
                case "NO":
                    tailPresent = false;
                    tailQuestonRelease = true;
                    break;
                default:
                    System.out.println("Please enter a valid response (yes or no)");
                    break;
            }
        } while (tailQuestonRelease == false);
        return tailPresent;
    }

    static String getSkinType()
    {
        String skinType = "";
        do 
        {
            switch (stringInput("What type of skin does this animal have?")) 
            {
                case "fur":
                case "Fur":
                case "FUR":
                    skinType = "fur";
                    break;
                case "scales":
                case "Scales":
                case "SCALES":
                    skinType = "scales";
                    break;
                case "feathers":
                case "Feathers":
                case "FEATHERS":
                    skinType = "feathers";
                    break;
                case "skin":
                case "Skin":
                case "SKIN":
                    skinType = "skin";
                    break;
                default:
                    System.out.println("Please enter a valid response (fur, scales, feathers, or skin)");
                    break;
            }
        } while (skinType == "");
        return skinType;
    }
    
    public static void main(String[] args) 
    {
        String playAgain = "";
        do
        {
            String climate = "";
            int legCount = -1;
            boolean tailPresent = false;
            String skinType = "";
            String animalNameGuess = "";

            System.out.println("Welcome to the Guessing Game!\nI want you to think of an animal\n");

            climate = getClimate();
            if (climate.equals("tropical")) 
            {
                legCount = getLegCount();
                if (legCount == 2) 
                {
                    skinType = getSkinType();
                    if (skinType.equals("feathers")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Bird";
                        } else {
                            animalNameGuess = "Chick";
                        }
                    } else if (skinType.equals("scales")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Lizard";
                        } else 
                        {
                            animalNameGuess = "Salamander";
                        }
                    } else if (skinType.equals("fur")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Kangaroo";
                        } else 
                        {
                            animalNameGuess = "Koala";
                        }
                    }
                } else if (legCount == 4) 
                {
                    skinType = getSkinType();
                    if (skinType.equals("feathers")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Pegasus";
                        } else 
                        {
                            animalNameGuess = "doesnt exist";
                        }
                    } else if (skinType.equals("scales")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Crocodile";
                        } else 
                        {
                            animalNameGuess = "Turtle";
                        }
                    } else if (skinType.equals("fur")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Cat";
                        } else 
                        {
                            animalNameGuess = "Dog";
                        }
                    }
                } else if (legCount == 8) 
                {
                    animalNameGuess = "Spider";
                } else if (legCount == 0) 
                {
                    animalNameGuess = "Snake";
                }
            } else if (climate.equals("temperate")) 
            {
                legCount = getLegCount();
                if (legCount == 2) 
                {
                    skinType = getSkinType();
                    if (skinType.equals("feathers")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Chicken";
                        } else 
                        {
                            animalNameGuess = "Chick";
                        }
                    } else if (skinType.equals("scales")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Lizard";
                        } else 
                        {
                            animalNameGuess = "Salamander";
                        }
                    } else if (skinType.equals("fur")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Chimpanzee";
                        } else 
                        {
                            animalNameGuess = "Human";
                        }
                    }
                } else if (legCount == 4) 
                {
                    skinType = getSkinType();
                    if (skinType.equals("feathers")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Pigeon";
                        } else 
                        {
                            animalNameGuess = "doesnt exist";
                        }
                    } else if (skinType.equals("scales")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Aligator";
                        } else 
                        {
                            animalNameGuess = "Turtle";
                        }
                    } else if (skinType.equals("fur")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Cat";
                        } else 
                        {
                            animalNameGuess = "Dog";
                        }
                    }
                } else if (legCount == 8) 
                {
                    animalNameGuess = "Spider";
                } else if (legCount == 0) 
                {
                    animalNameGuess = "Snake";
                }
            } else if (climate.equals("polar")) 
            {
                legCount = getLegCount();
                if (legCount == 2) 
                {
                    if (skinType.equals("feathers")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Penguin";
                        } else 
                        {
                            animalNameGuess = "Chick";
                        }
                    } else {
                        animalNameGuess = "I give up, you win";
                    }
                } else if (legCount == 4) 
                {
                    if (skinType.equals("fur")) 
                    {
                        tailPresent = getTailPresent();
                        if (tailPresent) 
                        {
                            animalNameGuess = "Polar Bear";
                        }
                        else 
                        {
                            animalNameGuess = "I give up, you win";
                        }
                    }
                    else 
                    {
                        animalNameGuess = "I give up, you win";
                    }
                } else 
                {
                    animalNameGuess = "I give up, you win";
                }
            }

            System.out.println("Your animal is a " + animalNameGuess);

            String correct = stringInput("Was I correct? (yes or no)");
            if (correct.equals("yes")) 
            {
                System.out.println("Yay!");
            } else 
            {
                System.out.println("I'm sorry, I'll try harder next time");
            }
            playAgain = stringInput("Would you like to play again? (yes or no)");

        } while (playAgain.equals("yes"));
    }   
    
}       


/*  Logic Tree
Tropical - 2 legs - feathers - yes tail ("Bird")
Tropical - 2 legs - feathers - no tail ("Chick")
Tropical - 2 legs - scales - yes tail ("Lizard")
Tropical - 2 legs - scales - no tail ("Salamander")
Tropical - 2 legs - fur - yes tail ("Kangaroo")
Tropical - 2 legs - fur - no tail ("Koala")
Tropical - 4 legs - feathers - yes tail ("Pegasus")
Tropical - 4 legs - feathers - no tail ("doesnt exist")
Tropical - 4 legs - scales - yes tail ("Crocodile")
Tropical - 4 legs - scales - no tail ("Turtle")
Tropical - 4 legs - fur - yes tail ("Cat")
Tropical - 4 legs - fur - no tail ("Dog")
Tropical - 8 legs - ("Spider")
Tropical - 0 legs - ("Snake")
Temperate - 2 legs - feathers - yes tail ("Chicken")
Temperate - 2 legs - feathers - no tail ("Chick")
Temperate - 2 legs - scales - yes tail ("Lizard")
Temperate - 2 legs - scales - no tail ("Salamander")
Temperate - 2 legs - fur - yes tail ("Chimpanzee")
Temperate - 2 legs - fur - no tail ("Human")
Temperate - 4 legs - feathers - yes tail ("Pigeon")
Temperate - 4 legs - feathers - no tail ("doesnt exist")
Temperate - 4 legs - scales - yes tail ("Aligator")
Temperate - 4 legs - scales - no tail ("Turtle")
Temperate - 4 legs - fur - yes tail ("Cat")
Temperate - 4 legs - fur - no tail ("Dog")
Temperate - 8 legs - ("Spider")
Temperate - 0 legs - ("Snake")
Polar - 2 legs - feathers - yes tail ("Penguin")
Polar - 2 legs - feathers - no tail ("Chick")
Polar - 4 legs - fur - yes tail ("Polar Bear") 

The computer should then ask questions for the user to answer. These questions should be a 
combination of yes/no, text based and number based.  YOU HAVE TO INCLUDE AT LEAST 2 OF EACH.
The decision tree should be at least 4 questions deep
2 String values other than simple yes/no checks
2 int and/or double values 
1 comparison of at least && or || logical operators
Lastly it should contain at least two questions that have more than two outcomes

*/