import java.util.*;

class DumplingThread extends Thread{
    Sell s;
    String name;
    DumplingThread(Sell s,String name){
        this.s=s;
        this.name=name;
    }
    public void run(){
        s.sell(name);
    }
}

class Sell{
    int pork=5000;
    int beef=3000;
    int vegetable=1000;
    public synchronized void sell(String name){
        try{
            Thread.sleep(3000);
            System.out.print(name+":");
        }catch(InterruptedException e) {}
        if(pork<10 && beef<10 && vegetable<10){
            System.out.println("水餃賣完了");
            System.exit(0);
        }else{
            int sell=(int)(Math.random()*41)+10;
            int dumpling=(int)(Math.random()*3)+1;
            switch(dumpling){
                case 1:
                    pork=pork-sell;
                    if(pork>=10){
                        System.out.println("買"+sell+"顆豬肉水餃，剩下"+pork+"顆豬肉水餃");
                    }else{
                        System.out.println("豬肉水餃賣完了");
                    }
                    break;
                case 2:
                    beef=beef-sell;
                    if(beef>=10){
                        System.out.println("買"+sell+"顆牛肉水餃，剩下"+beef+"顆牛肉水餃");
                    }else{
                        System.out.println("牛肉水餃賣完了");
                    }
                    break;
                case 3:
                    vegetable=vegetable-sell;
                    if(vegetable>=10){
                        System.out.println("買"+sell+"顆蔬菜水餃，剩下"+vegetable+"顆蔬菜水餃");
                    }else{
                        System.out.println("蔬菜水餃賣完了");
                    }
                    break;
            }
        }
    }
    public void startBuy(int num){
        DumplingThread[] customer=new DumplingThread[num];
        for(int i=0;i<customer.length;i++){
            String name="buyer"+(i+1);
            customer[i]=new DumplingThread(this,name);
            customer[i].start();
        }
    }
}

public class A1093306_0512_1{
    public static void main(String[] args) throws Exception {
        int customer;
        Scanner input=new Scanner(System.in);
        System.out.println("請輸入顧客數量:");
        customer=input.nextInt();
        Sell s=new Sell();
        s.startBuy(customer);
    }
}