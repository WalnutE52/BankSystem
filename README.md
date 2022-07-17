# W16_R5_Group1_Assignment1

This is an example file with default selections.

## Table of Contents
- [Background](#Background)
- [Install](#Install)
- [Usage](#Usage)
    - [Setup](#Setup)
    - [Run](#Run)
    - [Test](#Test)
- [Maintainers](#Maintainers)
- [Contributors](#Contributors)
- [License](#License)

## Background
This project is an ATM operating system. Users can withdraw, deposit and query the balance through this system. At the same time, there is an administrator who can increase the balance in the ATM. It is worth noting that the ATM supports Australian dollars.

## Install
Installation options are not available for this project. This project is builded on [gradle] 6.8.3 with [java] 11. Please set up the right system environment.

[gradle]: https://gradle.org/install/
[java]: https://java.com/en/download/help/download_options.html

```
$git clone git@github.sydney.edu.au:SOFT2412-2021S2/W16_R5_Group1_Assignment1.git
```

## Usage

### Setup

You can find configuration.txt file in folder **src/main/resources**. You could set up your own ATM profile and bank card list by this configuration file. Also, please do not change the *test_atm.txt* and *test_cardlist_txt*. They are used to test the program.

### Run

You could use gradle run to run this program.
1. Firstly,  it would let you type your card number. 
2. If your card are invaild (in some special state) , you would enable to use the card. 
3. If card is valid, it would ask  the pin of the card, if you type wrong pin three times. It would lock your card. 
4. After vetify your card, system would ask what kind of service you want to choose, including withdrawal, deposit, and check balance.
5. If you choose withdrawal, the system would ask how much would you like to withdraw, then, you can withdraw your money. However, if the money in bank or your card  balance are not enough or insufficient currency of some denomination in ATM, system would tell you these errors happen.
* For deposit, you need type the number of 5 dollar, 10 dollar, 20 dollar, 50 dollar 100 dollar  respectively 
> Eg. 0 0 0 0 1 for deposit 100 dollar. 

6. If you choose check balance, the system would show you the balance of your card. 

* Beside, this system can add cash to ATM by manager. When the system ask your card num you can type 0 which is manager card number. Then type the pin you write in you caldlist file. 

* You can add cash as same as step of deposit, but in this case, you should type number for Australian dollars of all denominations. **Remember, the limitation of ATM is that each denomination cash can only be stored 500.**
> Eg. 1 0 0 0 0 0 0 0 0 0 0 for add 5 cents to the ATM


### Test

You could use **gradle test** to test whether the software can run normally in your local area.


## Maintainers
[@YuchengPeng]
[@HuirongGuo]

[@YuchengPeng]: https://github.sydney.edu.au/ypen9276
[@HuirongGuo]: https://github.sydney.edu.au/hguo0780

## Contributors

<a href="https://github.sydney.edu.au/SOFT2412-2021S2/W16_R5_Group1_Assignment1/graphs/contributors"><img src="contributor.png"></a>


## License

MIT © [Yucheng Peng](https://github.sydney.edu.au/ypen9276)
