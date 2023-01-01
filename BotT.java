package my.uum;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * This class is for operation the bot
 */
public class BotT extends TelegramLongPollingBot {



        private String step ="start"; //for sepatate message and select
    private String msg ="";


    /**
     * This method is for received message  frm user and sent message to user
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();


        if (update.hasMessage()) {
            Message user_msg = update.getMessage(); //the content user key in and get message
            String chatId = update.getMessage().getChatId().toString();



            if (user_msg.hasText()) {
                String command = user_msg.getText(); //get text


                try {


                        if(command.equals("/start")) {
                            message.setText("Hi,welcome to the booking service center . \nAre you making booking for yourself?\n\nReply 1. Yes\nReply 2. No\nReply 0. Back to menu");
                            message.setChatId(update.getMessage().getChatId().toString());

                            execute(message);

                            step = BotConstant.SELECT5;
                        }
                        else{
                            msg=command;
                        }

                 if (step.equals(BotConstant.SELECT5)) {

                        if (command.equals("0")) {
                            sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                            step =BotConstant.SELECTMENU;

                        }else if (command.equals("2")) {
                            sendText(chatId, "Thank You");

                        } else if((command.startsWith("/")) ){
                            msg=command;

                        }
                        else if (command.equals("1")) {

                            sendText(chatId, "Please provide your booking date(dd-mm-yyyy) \n\nReply 0.Main menu");
                            step=BotConstant.SELECT6;
                        }

                    }

                       else if (step.equals(BotConstant.SELECT6)) {


                            if (command.equals("0")) {
                                sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                                step =BotConstant.SELECTMENU;

                            } else if((command.startsWith("/")) ){
                                msg=command;

                            }
                         else{
                         msg = command;
                         User_list.setBooking_Date(msg);

                         sendText(chatId, "Please provide your booking time(Ex:9:00am) \n\nReply 0.Main menu");
                         step=BotConstant.SELECT7;
                     }
                    }

                        else if (step.equals(BotConstant.SELECT7)) {

                            if (command.equals("0")) {
                                sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                                step =BotConstant.SELECTMENU;

                            } else if((command.startsWith("/")) ){
                                msg=command;

                            }
                            else {
                                msg = command;
                                User_list.setBooking_Time(msg);


                                String response=  SQLite. displayAvailbleRoom();

                                sendText(chatId, "This list show available room"
                                        +"\n"+response);


                                sendText(chatId, " Please insert room id you want. If no room you want ,you can change the booking date\n\nReply 1.Change the Booking Date\nReply 0.Main Menu");

                                step=BotConstant.SELECT9;

                              //  sendText(chatId, "Please check your info.Are these correct?\n\nIc Number: "+ User_list.getICNO()+"\nStaff ID: "+ User_list.getStaff_id()+"\nName: "+ User_list.getName() +"\nTel No: "+ User_list.getMobile_TelNo()+"\nEmail: "+ User_list.getEmail()
                              //          +"\nPurpose of Booking: "+ User_list.getPurpose()+"\nBooking Date: "+ User_list.getBooking_Date()+"\nBooking Time: "+ User_list.getBooking_Time()+"\n\n1.Yes\n0.Main menu");


                               // step=BotConstant.SELECT8;
                            }

                        }
                        else if(step.equals(BotConstant.SELECT9)) {
                     if (command.equals("0")) {
                         sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                         step = BotConstant.SELECTMENU;

                     } else if ((command.startsWith("/"))) {
                         msg = command;

                     } else if (command.equals("1")) {
                         sendText(chatId, "Please provide your booking date \n\nReply 0.Main menu");
                         step = BotConstant.SELECT6;

                         //step = BotConstant.ChangeDate;

                     } else {
                         msg = command;

                     String response = SQLite.bookRoom(msg);
                  String recall ="Room id not available.Please insert again.";


                    if (response.equals(recall)){
                        sendText(chatId, response);
                         step=BotConstant.SELECT9;

                     } else {
                         sendText(chatId, response);
                         sendText(chatId, "Please provide your Ic Number(xxxxxxx08xxxx)\n\nReply 0.Main Menu");

                         step = BotConstant.SELECT;
                     }

                     }
                 }
                 else if(step.equals(BotConstant.WRITE_MSG)){
                     if((command.startsWith("/")) ){
                         msg=command;

                     }
                     else{

                     }
                 }
                  else if(step.equals(BotConstant.SELECT11)){
                     sendText(chatId, "Please provide your Ic Number(xxxxxxx08xxxx)\n\nReply 0.Main Menu");
                     //step=BotConstant.SELECT;
                 }
                    else if (step.equals(BotConstant.SELECT)) {

                         if (command.equals("0")) {

                             sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                             step = BotConstant.SELECTMENU;

                         } else if ((command.startsWith("/"))) {
                             msg = command;

                         } else {
                             msg = command;
                             User_list.setICNO(msg);
                             sendText(chatId, "Please provide your staff ID\n\nReply 0.Main menu");
                             step = BotConstant.SELECT1;

                         }


                     } else if (step.equals(BotConstant.SELECT1)) {

                         if (command.equals("0")) {
                             sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                             step = BotConstant.SELECTMENU;

                         } else if ((command.startsWith("/"))) {
                             msg = command;

                         } else {
                             msg = command;
                             User_list.setStaff_id(msg);
                             sendText(chatId, "Please provide your name\n\nReply 0.Main menu");
                             step = BotConstant.SELECT2;
                         }

                     } else if (step.equals(BotConstant.SELECT2)) {

                         if (command.equals("0")) {
                             sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                             step = BotConstant.SELECTMENU;

                         } else if ((command.startsWith("/"))) {
                             msg = command;

                         } else {
                             msg = command;
                             User_list.setName(msg);

                             sendText(chatId, "Please provide your handphone number(Ex:0165444888)\n\nReply 0.Main menu");
                             step = BotConstant.SELECT3;
                         }

                     } else if (step.equals(BotConstant.SELECT3)) {

                         if (command.equals("0")) {
                             sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                             step = BotConstant.SELECTMENU;

                         } else if ((command.startsWith("/"))) {
                             msg = command;

                         } else {
                             msg = command;
                             User_list.setMobile_TelNo(msg);

                             sendText(chatId, "Please provide your email(Ex:xxx@gmail.com)\n\nReply 0.Main menu");
                             step = BotConstant.SELECT4;
                         }

                     } else if (step.equals(BotConstant.SELECT4)) {

                         if (command.equals("0")) {
                             sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                             step = BotConstant.SELECTMENU;

                         } else if ((command.startsWith("/"))) {
                             msg = command;

                         } else {
                             msg = command;
                             User_list.setEmail(msg);
                             sendText(chatId, "Please provide your purpose for booking(Training,Meeting,Event,etc)\n\nReply 0.Main menu");
                             step = BotConstant.SELECT8;
                         }

                     } else if (step.equals(BotConstant.SELECT8)) {

                         if (command.equals("0")) {
                             sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                             step = BotConstant.SELECTMENU;

                         } else if ((command.startsWith("/"))) {
                             msg = command;

                         } else {
                             msg = command;
                             User_list.setPurpose(msg);
                             sendText(chatId, "Please check your info.Are these correct?\n\nIc Number: "+ User_list.getICNO()+"\nStaff ID: "+ User_list.getStaff_id()+"\nName: "+ User_list.getName() +"\nTel No: "+ User_list.getMobile_TelNo()+"\nEmail: "+ User_list.getEmail()
                                   +"\nPurpose of Booking: "+ User_list.getPurpose()+"\nBooking Date: "+ User_list.getBooking_Date()+"\nBooking Time: "+ User_list.getBooking_Time()+"\n\n1.Yes\n0.Main menu");

                         step =BotConstant.SELECT10;
                         }

                     }else if (step.equals(BotConstant.SELECT10)) {

                     if (command.equals("0")) {
                         sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                         step = BotConstant.SELECTMENU;

                     } else if ((command.startsWith("/"))) {
                         msg = command;

                     } else if (command.equals("1")) {

                         sendText(chatId, "You are successfully make a booking");
                         SQLite.insertSS(User_list.getICNO(), User_list.getStaff_id(), User_list.getName(), User_list.getMobile_TelNo(), User_list.getEmail(), User_list.getPurpose(), User_list.getBooking_Date(), User_list.getBooking_Time());
                         SQLite.roomIc();
                     }

                 }







                        else if (step.equals(BotConstant.SELECTMENU)) {

                            if (command.equals("1")) {
                                sendText(chatId, "You can make a booking with command /start");

                            }
                            else if (command.equals("2")){

                                sendText(chatId, "You can cancel the booking with command /cancel");

                            }
                            else{
                                msg=command;
                            }

                        }

                    if( command.equals("/cancel")) {
                        sendText(chatId, "Pls insert your ICNO \n\nReply 0.Main menu");
                        step=BotConstant.Cancel1;

                    }

                    else if (step.equals(BotConstant.Cancel1)) {

                        if (command.equals("0")) {
                            sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");
                            step =BotConstant.SELECTMENU;

                        }
                        else if((command.startsWith("/")) ){
                            msg=command;

                        }
                        else {
                            msg = command;
                            String cancel= SQLite.cancel(msg);
                            sendText(chatId, cancel);
                            step= BotConstant.WRITE_MSG;

                        }

                    }

                    if(command.equals("/menu")){


                        sendText(chatId, "Would you like to enquire about the following? (Please select from below):\n\nReply 1.Make a booking\nReply 2.Cancel the booking");

                        step =BotConstant.SELECTMENU;

                    }

                    if(command.equals("/display")) {

                        message.setChatId(update.getMessage().getChatId().toString());
                        String response=SQLite.displayListAll();
                        message.setText("Display list of user  "+response+"\n\nReply 0. Back to menu");
                        execute(message);


                    }
                    else{
                        msg=command;
                    }



                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }


            }
        }

    }





    private void sendText(String chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        try {

            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }


    /**
     * This method is  return bot username
     * @return bot username
     */
    @Override
    public String getBotUsername() {
        // TODO
        return "s278884_A221_bot";
    }

    /**
     * This method is for return bot token
     * @return bot token
     */
    @Override
    public String getBotToken() {
        // TODO
        return "5717300937:AAGM0VpChvz7680oSc17BiyUacLhYP0dgBk";
    }
}
