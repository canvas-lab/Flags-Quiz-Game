package edu.curtin.comp2008.mad2020assignment1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * FlagData class contains an ArrayList of FlagFullStructure
 * This class has a createFlagData method that creates all the data that will be used in this project
 *
 * ref: all the questions and answers is taken from https://www.jetpunk.com
 */
public class FlagData
{
    //private field
    private ArrayList<FlagFullStructure> flagDataList;

    //default constructor
    public FlagData()
    {
        flagDataList = new ArrayList<>();
    }

    //alternate constructor
    public FlagData(ArrayList<FlagFullStructure> flagDataList)
    {
        this.flagDataList = flagDataList;
    }

    public ArrayList<FlagFullStructure> getFlagDataList()
    {
        return flagDataList;
    }

    public void setFlagDataList(ArrayList<FlagFullStructure> flagDataList)
    {
        this.flagDataList = flagDataList;
    }

    public int getFlagInt(int i)
    {
        return flagDataList.get(i).getFlag();
    }

    //get the size of the ArrayList flagDataList
    public int sizeFlagData()
    {
        return flagDataList.size();
    }


    /**
     *  ref: all the questions and answers is NOT mine and is taken from https://www.jetpunk.com
     *
     *  My 16 chosen flags are:
     *  1 Georgia (ge)
     *  2 Greece (gr)
     *  3 Hong Kong (hk)
     *  4 Italy (it)
     *  5 Japan (jp)
     *  6 Korea (kr)
     *  7 Lithuania (lt)
     *  8 Mexico (mx)
     *  9 Malaysia (my)
     *  10 Neetherlands (nl)
     *  11 Polland (pl)
     *  12 Qatar (qa)
     *  13 Rusia (ru)
     *  14 United Kingdom (uk)
     *  15 United States of America (us)
     *  16 Viet Nam (vn)
     *
     *  createFlagData method created all the flag data.
     *  First, it creates a list of the qna attributes, containing a questions, an ArrayList of 2 to 4 choices, one correct answer, point, penalty and isSpecial
     *  Then, it will add those created qna attributes to the flagDataList ArrayList. Each flagDataList contains a flag drawable integer and the qna attributes
     */
    public void createFlagData()
    {
        //initialize the QnA ArrayList and add the QnA attributes

        //1 Georgia (ge)
        //https://www.jetpunk.com/user-quizzes/63858/georgia-country-quiz
        ArrayList<QnA> flagAtt1 = new ArrayList<>();
        flagAtt1.add(new QnA("What is the capital city of Georgia?", Arrays.asList("Tbilisi", "Tiilisi", "Bililisi"), 1, 10, 5, false));
        flagAtt1.add(new QnA("Which country is bordering Georgia?", Arrays.asList("USA", "London", "Australia", "Russia"), 4, 30, 20, true));
        flagAtt1.add(new QnA("What sea which borders Georgia?", Arrays.asList("None", "Black Sea"), 2, 20, 8, false));
        flagAtt1.add(new QnA("What mountain range on Georgia's northern border?", Arrays.asList("Caucacus", "Cacacucus", "Coucoucus", "Ccoconus"), 1, 25, 6, false));
        flagAtt1.add(new QnA("What is the largest Georgian city on Black Sea?", Arrays.asList("Gurita", "Soviet Union", "Batumi", "Vasutu"), 3, 13, 8, false));

        //2 Greece (gr)
        //https://www.jetpunk.com/user-quizzes/8505/greece-country-quiz
        ArrayList<QnA> flagAtt2 = new ArrayList<>();
        flagAtt2.add(new QnA("What is the most famous temple in Greece?", Arrays.asList("Nonetharp", "Parthenon"), 2, 10, 1, false));
        flagAtt2.add(new QnA("What is the empire that Greece became independent from in 1830?", Arrays.asList("Ottoman", "Moemane", "Fairy Land"), 1, 20, 15, false));
        flagAtt2.add(new QnA("What sea is between Greece and Turkey?", Arrays.asList("Helmoa Sea", "Lagoona Sea", "Aegean Sea", "Hayden Sea"), 3, 10, 13, false));
        flagAtt2.add(new QnA("What is the former currency of Greece?", Arrays.asList("Drachma", "Ackmarha"), 1, 20, 13, false));
        flagAtt2.add(new QnA("Where is the Oracle city in Greece?", Arrays.asList("Alpha", "Loscatus", "Delphi"), 3, 20, 18, false));
        flagAtt2.add(new QnA("Where is the location of Greece's Peninsula?", Arrays.asList("Balaga", "Galaga", "Malakan", "Balkan"), 4, 10, 5, false));
        flagAtt2.add(new QnA("What is the capital city of Greece?", Arrays.asList("Thenos", "Athena", "Amriha", "Kathalanu"), 2, 10, 3, true));

        //3 Hong Kong (hk)
        //https://www.jetpunk.com/user-quizzes/38391/hong-kong-city-quiz
        ArrayList<QnA> flagAtt3 = new ArrayList<>();
        flagAtt3.add(new QnA("What is the currency of Hong Kong?", Arrays.asList("Hong Kong Dollar", "Yuan"), 2, 26, 8, false));
        flagAtt3.add(new QnA("What year Hong Kong returned to China?", Arrays.asList("1997", "1948", "2000", "1623"), 1, 16, 3, false));
        flagAtt3.add(new QnA("What is the literal meaning of the name 'Hong Kong'?", Arrays.asList("Fragrant Harbour", "Harmonious Family", "Independence and Freedom", "Family"), 1, 22, 12, true));
        flagAtt3.add(new QnA("What war that led to the cession in Hong Kong?", Arrays.asList("World War II", "First Opium War"), 2, 12, 2, false));
        flagAtt3.add(new QnA("What Chinese dialect spoken by most Hong Kong population?", Arrays.asList("Mandarin", "Hokien", "Cantonese"), 3, 9, 2, false));

        //4 Italy (it)
        //https://www.jetpunk.com/quizzes/italy-multiple-choice
        ArrayList<QnA> flagAtt4 = new ArrayList<>();
        flagAtt4.add(new QnA("Which of these cities is furthest north in Italy?", Arrays.asList("Rome", "Venice", "Paris"), 3, 26, 9, false));
        flagAtt4.add(new QnA("Is Florence on the coast?", Arrays.asList("Yes", "No"), 2, 16, 3, false));
        flagAtt4.add(new QnA("Who among the following was a Roman emperor?", Arrays.asList("Calilima", "Caligula", "Gilagula", "Gorifola"), 2, 23, 12, false));
        flagAtt4.add(new QnA("Who painted the ceiling of the Sistine Chapel?", Arrays.asList("Leonardo", "Michelangelo", "Donald", "Hayden"), 2, 22, 13, false));
        flagAtt4.add(new QnA("What happens at La Scala?", Arrays.asList("Operas", "Mass execution"), 1, 13, 3, true));
        flagAtt4.add(new QnA("Which of these countries is geographically closest to Italy (as the crow flies)?", Arrays.asList("Croatia", "London"), 1, 4, 1, false));

        //5 Japan (jp)
        //https://www.jetpunk.com/quizzes/japan-country-quiz
        ArrayList<QnA> flagAtt5 = new ArrayList<>();
        flagAtt5.add(new QnA("What is Japanese currency?", Arrays.asList("Yen", "Japan Dollar", "Yuan", "Joon"), 1, 26, 16, true));
        flagAtt5.add(new QnA("What is the capital city of Japan?", Arrays.asList("Tokyo", "Kyoto", "Nagasaki"), 1, 16, 9, false));
        flagAtt5.add(new QnA("What is the former name of 'Tokyo'?", Arrays.asList("Deko", "Edo"), 2, 13, 7, false));
        flagAtt5.add(new QnA("What is the name for Japanese comics?", Arrays.asList("Manhua", "Manhwa", "Manga", "Comico"), 3, 14, 9, false));
        flagAtt5.add(new QnA("Which city is the Southernmost prefecture where karate was invented?", Arrays.asList("Kyoto", "Tokyo", "Okinawa", "Hiroshima"), 3, 24, 15, false));
        flagAtt5.add(new QnA("What is the highest mountain is Japan?", Arrays.asList("Mount Fuuji", "Mount Fujii", "Mount Fuji", "Mount Fuuji"), 3, 25, 17, false));
        flagAtt5.add(new QnA("What is Japanese nickname?", Arrays.asList("Land of the Rising Sun", "Land of the Freedom"), 1, 36, 22, false));
        flagAtt5.add(new QnA("Where is the former capital and city of temples in Japan?", Arrays.asList("Osaka", "Tokyo", "Kyoto", "Nagasaki"), 3, 6, 2, false));
        flagAtt5.add(new QnA("What is the second most populous urban area in Japan?", Arrays.asList("Osaka", "Kyoto"), 1, 5, 2, false));
        flagAtt5.add(new QnA("What is the main islands in Japan?", Arrays.asList("Honshu", "Sushon", "Fuhonshu", "Suhon"), 1, 7, 5, false));

        //6 South Korea (kr)
        //https://www.jetpunk.com/quizzes/south-korea-country-quiz
        ArrayList<QnA> flagAtt6 = new ArrayList<>();
        flagAtt6.add(new QnA("What is the capital city of South Korea?", Arrays.asList("Incheon", "Busan", "Seoul", "Daegu"), 3, 17, 13, false));
        flagAtt6.add(new QnA("What is the second most populous urban area in South Korea?", Arrays.asList("Incheon", "Busan", "Daegu", "Hongdae"), 2, 12, 4, false));
        flagAtt6.add(new QnA("What is the largest corporation in South Korea?", Arrays.asList("Samsung", "Apple", "Nokia"), 1, 13, 11, false));
        flagAtt6.add(new QnA("What is South Korea's currency?", Arrays.asList("Yen", "Won"), 2, 47, 23, true));
        flagAtt6.add(new QnA("What is the largest island in South Korea?", Arrays.asList("Jeju", "Juje", "Minjae", "Donghae"), 1, 11, 8, false));
        flagAtt6.add(new QnA("What is the NOT most common name in South Korea?", Arrays.asList("Kim", "Lee", "Ji", "Park"), 3, 16, 8, false));
        flagAtt6.add(new QnA("What is the strip of land between South Korea and North Korea?", Arrays.asList("Demilitarized Zone", "Military War Zone"), 1, 6, 2, false));
        flagAtt6.add(new QnA("What is South Korean fermented cabbage called?", Arrays.asList("Kimchi", "Japchae"), 1, 7, 2, false));

        //7 Lithuania (lt)
        //https://www.jetpunk.com/user-quizzes/273453/lithuania-country-quiz
        ArrayList<QnA> flagAtt7 = new ArrayList<>();
        flagAtt7.add(new QnA("What is the capital city of Lithuania?", Arrays.asList("Vilnius", "Vuvilnius", "Viinius"), 1, 7, 6, false));
        flagAtt7.add(new QnA("What second most populous city in Lithuania?", Arrays.asList("Kaunas", "Nasi", "Nasimu", "Oranh"), 1,  9, 5, false));
        flagAtt7.add(new QnA("What is national bird of Lithuania?", Arrays.asList("Black Stork", "White Stork"), 2, 7, 4, false));
        flagAtt7.add(new QnA("Which is NOT the countries which border Lithuania?", Arrays.asList("Belarus", "Poland", "Russia", "France"), 4, 8, 3, false));
        flagAtt7.add(new QnA("What is the Sea which Lithuania borders?", Arrays.asList("Black Sea", "Baltic Sea"), 2, 25, 21, true));

        //8 Mexico (mx)
        //https://www.jetpunk.com/quizzes/mexico-country-quiz
        ArrayList<QnA> flagAtt8 = new ArrayList<>();
        flagAtt8.add(new QnA("What is the currency of Mexico?", Arrays.asList("Pseto", "Pesto", "Peso", "Pasta"), 3, 27, 26, true));
        flagAtt8.add(new QnA("What is the biggest export in Mexico?", Arrays.asList("oil", "clock", "wood"), 1, 17, 14, false));
        flagAtt8.add(new QnA("What is the longest river in Mexico?", Arrays.asList("Donald Duck", "Rio Grande", "Grande Bay", "Hola"), 2, 19, 16, false));
        flagAtt8.add(new QnA("What is the peninsula south of California?", Arrays.asList("Baja Peninsula", "Batu Bata Peninsula", "Raja Peninsula", "Mitos Peninsula"), 1, 9, 6, false));
        flagAtt8.add(new QnA("What is NOT the bordering countries of Mexico?", Arrays.asList("United States", "Russia"), 2, 14, 9, false));

        //9 Malaysia (my)
        //https://www.jetpunk.com/user-quizzes/273453/malaysia-country-quiz
        ArrayList<QnA> flagAtt9 = new ArrayList<>();
        flagAtt9.add(new QnA("What is the capital city of Malaysia?", Arrays.asList("Jakarta", "Kuala Lumpur"), 2, 8, 5, false));
        flagAtt9.add(new QnA("What countries do NOT border Malaysia?", Arrays.asList("Indonesia", "Thailand", "Laos", "Brunei"), 3, 7, 4, false));
        flagAtt9.add(new QnA("What is the currecy of Malaysia?", Arrays.asList("Rupiah", "Ringgit"), 2, 9, 3, false));
        flagAtt9.add(new QnA("What is the national fruit of Malaysia?", Arrays.asList("Durian", "Rambutan", "Jengkol"), 1, 12, 6, false));
        flagAtt9.add(new QnA("What is the tallest building in Malaysia?", Arrays.asList("Monas Towers", "Petronas Towers", "Kromosha Towers", "Hoho Tower"), 2, 2, 1, false));

        //10 Netherlands (nl)
        //https://www.jetpunk.com/quizzes/netherlands-country-quiz
        ArrayList<QnA> flagAtt10 = new ArrayList<>();
        flagAtt10.add(new QnA("What is NOT the most populous cities in Netherlands?", Arrays.asList("Amsterdam", "Rotterdam", "The Mague", "The Hague"), 3, 8, 5, false));
        flagAtt10.add(new QnA("What is Netherlands' official language?", Arrays.asList("Netherlandish", "Dutch", "English", "Nesse"), 2, 9, 4, false));
        flagAtt10.add(new QnA("What is the name for the heir to the throne in Netherlands?", Arrays.asList("Prince of Orange", "Prince from Shoujo", "Prince of the Red", "Prince of the most Handsome"), 1, 4, 3, false));
        flagAtt10.add(new QnA("What is the former currency of Netherlands?", Arrays.asList("Guilder", "Loam", "Oran"), 1, 3, 2, false));
        flagAtt10.add(new QnA("What is the largest bank in Netherlands?", Arrays.asList("GIN", "NIG", "ING", "GNI"), 3, 2, 1, false));

        //11 Poland (pl)
        //https://www.jetpunk.com/quizzes/poland-country-quiz
        ArrayList<QnA> flagAtt11 = new ArrayList<>();
        flagAtt11.add(new QnA("What is the capital city of Poland?", Arrays.asList("Warsaw", "Wadurh", "Wudrah", "Hurdra"), 1, 3, 2, false));
        flagAtt11.add(new QnA("What is the former capital of Poland?", Arrays.asList("Krsakow", "Krakow"), 2, 18, 15, false));
        flagAtt11.add(new QnA("What is the second biggest city in Poland?", Arrays.asList("Krakow", "Krsakow"), 1, 12, 5, false));
        flagAtt11.add(new QnA("What in the longest river in Poland?", Arrays.asList("Amber", "Red River", "Vistula", "Jabshua"), 3, 22, 19, false));
        flagAtt11.add(new QnA("What is the currency of Poland?", Arrays.asList("Polar", "Zloty", "Polish", "Joyush"), 2, 19, 13, false));
        flagAtt11.add(new QnA("What is the main city of Upper Silesia in Poland?", Arrays.asList("Katowish", "Visitia"), 1, 7, 5, false));

        //12 Qatar (qt)
        //https://www.jetpunk.com/user-quizzes/273453/qatar-country-quiz
        ArrayList<QnA> flagAtt12 = new ArrayList<>();
        flagAtt12.add(new QnA("What is the capital city of Qatar?", Arrays.asList("Hado", "Doha", "Odha"), 2, 13, 9, false));
        flagAtt12.add(new QnA("What is the official language of Qatar?", Arrays.asList("English", "Qatar", "Arabic", "Qaras"), 3, 2, 1, false));
        flagAtt12.add(new QnA("What is the currency of Qatar?", Arrays.asList("Qatar Ak", "Qata Rolay", "Qatar Riyal", "Qaeas Lo"), 3, 7, 4, false));
        flagAtt12.add(new QnA("What is the primary export of Qatar?", Arrays.asList("oil", "wood"), 1, 8, 6, false));
        flagAtt12.add(new QnA("What is the penalty for alcohol consumption in Qatar?", Arrays.asList("2.5 year prison", "lashes", "$5000 fine", "$1000 fine"), 2, 4, 2, false));

        //13 Russia (ru)
        //https://www.jetpunk.com/quizzes/russia-country-quiz
        ArrayList<QnA> flagAtt13 = new ArrayList<>();
        flagAtt13.add(new QnA("What is the capital city of Russia?", Arrays.asList("Moscow", "St. James", "St. Petersburg", "St. Deds"), 1, 1, 1, false));
        flagAtt13.add(new QnA("What is the capital prior to 1918 in Russia?", Arrays.asList("St. James", "St. Petersburg", "St. Champion", "St. Uuiku"), 2, 3, 2, false));
        flagAtt13.add(new QnA("What is the sea bordering Russia?", Arrays.asList("White Sea", "Red Sea", "None", "None are correct"), 4, 9, 6, false));
        flagAtt13.add(new QnA("What is the sea bordering Russia?", Arrays.asList("Black Sea", "Red Sea", "Whte Sea", "Moshao Sea"), 1, 22, 16, false));
        flagAtt13.add(new QnA("What is the name for Soviet prison camps in Russia?", Arrays.asList("Uluga Prison Camps", "Gulags", "Gukags"), 2, 7, 5, false));

        //14 United Kingdom (uk)
        //https://www.jetpunk.com/user-quizzes/139806/united-kingdom-country-quiz
        ArrayList<QnA> flagAtt14 = new ArrayList<>();
        flagAtt14.add(new QnA("What is the longest river in United Kingdom?", Arrays.asList("River Severn", "Red River", "Hamilton River", "Asdsa River"), 1, 31, 22, false));
        flagAtt14.add(new QnA("What is the oldest university in United Kingdom?", Arrays.asList("Harvard", "Mitchelle", "Oxford", "Jordan"), 3,  13, 12, false));
        flagAtt14.add(new QnA("What is the tallest building in United Kingdom?", Arrays.asList("The Shard", "Ben Nevis", "Jos Ajaik"), 1, 23, 19, false));
        flagAtt14.add(new QnA("What is the busiest airport in United Kingdom?", Arrays.asList("London Heathrow", "Honola Airport"), 1, 33, 25, false));
        flagAtt14.add(new QnA("What is the currency of United Kingdom?", Arrays.asList("US Dollar", "Pound Sterling"), 2, 4, 2, false));

        //15 United States of America (us)
        //https://www.jetpunk.com/quizzes/name-state-capitals
        ArrayList<QnA> flagAtt15 = new ArrayList<>();
        flagAtt15.add(new QnA("What is the capital city of Alabama in United States of America?", Arrays.asList("Gomomnery", "Montgomery"), 2, 4, 2, false));
        flagAtt15.add(new QnA("What is the capital city of Alaska in United State?", Arrays.asList("June", "Juneau", "Mujune"), 2, 6, 3, false));
        flagAtt15.add(new QnA("What is the capital city of Arizona in United States of America?", Arrays.asList("Phoenix", "Saint Paul"), 1,  3, 1, false));
        flagAtt15.add(new QnA("What is the capital city of Arkansas in United States of America?", Arrays.asList("Salem", "Little Rock"), 2, 6, 2, false));
        flagAtt15.add(new QnA("What is the capital city of California in United States of America?", Arrays.asList("Atlanta", "Sacramento", "Chilemonia", "Austin"), 2, 7, 4, false));
        flagAtt15.add(new QnA("What is the capital city of Colorado in United States of America?", Arrays.asList("Denver", "Coliumbis", "Santa Fe", "Dwek Ok"), 1, 8, 5, false));

        //16 Viet Nam (vn)
        //https://www.jetpunk.com/quizzes/vietnam-country-quiz
        ArrayList<QnA> flagAtt16 = new ArrayList<>();
        flagAtt16.add(new QnA("What is the capital city of Vietnam?", Arrays.asList("Nohai", "Hanoi", "Lanka"), 2, 34, 24, false));
        flagAtt16.add(new QnA("What is the currency of Vietnam?", Arrays.asList("Trong", "Dong", "Yen", "Nyet"), 2, 50, 43, true));
        flagAtt16.add(new QnA("What is Vietnamese New Year called as?", Arrays.asList("Net", "Tet"), 2, 32, 22, false));
        flagAtt16.add(new QnA("What is the peninsula on which Vietnam sits at?", Arrays.asList("Indochina", "Indonesia", "Mdne Peninsula", "Nal Eis"), 1, 17, 12, false));
        flagAtt16.add(new QnA("What is the country that colonized Vietnam?", Arrays.asList("Japan", "US", "France", "England"), 3, 11, 5, false));
        flagAtt16.add(new QnA("What type of food is pho??", Arrays.asList("rice", "meat", "soup", "milk"), 3, 13, 6, false));
        flagAtt16.add(new QnA("What is the river that flows through the capital in Vietnam?", Arrays.asList("Halong", "Red", "Bai", "Kjo"), 2, 3, 1, false));
        flagAtt16.add(new QnA("What is the most populous city in Vietnam?", Arrays.asList("Ho Chi Minh City", "Hanoi City", "Hoal City", "Hajes City"), 1, 9, 5, false));
        flagAtt16.add(new QnA("What is the former name of the most populous city in Vietnam?", Arrays.asList("Halong", "Saigon", "Tronh", "Nguyen"), 2, 16, 12, false));


        //add each of the QnA ArrayList created to the respective flagDataList ArrayList.
        //each flagDataList contains a flag drawable integer and the qna attributes
        flagDataList.add(new FlagFullStructure(R.drawable.flag_ge, flagAtt1));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_gr, flagAtt2));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_hk, flagAtt3));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_it, flagAtt4));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_jp, flagAtt5));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_kr, flagAtt6));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_lt, flagAtt7));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_mx, flagAtt8));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_my, flagAtt9));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_nl, flagAtt10));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_pl, flagAtt11));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_qa, flagAtt12));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_ru, flagAtt13));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_uk, flagAtt14));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_us, flagAtt15));
        flagDataList.add(new FlagFullStructure(R.drawable.flag_vn, flagAtt16));
    }
}
