package com.example.arthurwang.helloworld.nov;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MergeActivity extends AppCompatActivity {

    private static final int FINISH_CHILD_THREAD = 10;
    private static final int CHILD_THREAD_EXCEPTION = 11;

    private MyThread myThread;


    private LinearLayout mInflatedStart;
    private ViewStub mStub;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private TextView mTvHandler;
    private Button mBtnStartThread;
    private EditText mEtTestThread;

    private void assignViews() {
        mInflatedStart = findViewById(R.id.inflatedStart);
        mStub = findViewById(R.id.stub);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mTvHandler = findViewById(R.id.tv_handler);
        mBtnStartThread = findViewById(R.id.btn_start_thread);
        mEtTestThread = findViewById(R.id.et_test_thread);
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);


        assignViews();

//        mStub.setOnInflateListener(inflateListener);
//        mBtn1.setOnClickListener(clickListener);
//        mBtn2.setOnClickListener(clickListener);
//        mBtn3.setOnClickListener(clickListener);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testANR();
//            }
//        }).start();
//
//        SystemClock.sleep(10);
//
//        initView();




//        String s1 = "Hello";
//        String s2 = new String("Hello");
//
//        if (s1 == s2) {
//            KLog.e("== is 相等");
//        } else {
//            KLog.e("== is 不相等");
//        }
//
//        if (s1.equals(s2)) {
//            KLog.e("== is 相等");
//        } else {
//            KLog.e("== is 不相等");
//        }


//        Vector numberNames = new Vector(3, 1);
//
//        KLog.e("向量初始大小：" + numberNames.size());
//        KLog.e("向量初始容量： " + numberNames.capacity());
//
//        numberNames.addElement(12);
//        numberNames.addElement(Double.valueOf(1.111));
//        numberNames.addElement(new Double(2.222));
//        numberNames.addElement(new Double(3.333));
//        numberNames.addElement(new Double(4.444));
//
//        KLog.e("加入5个元素后的容量： " + numberNames.capacity());
//        numberNames.addElement(new Date());
//
//        if (numberNames.contains(2.222)) {
//            KLog.e("向量中有元素2.222");
//        }
//
//        KLog.e("第三个元素为： " + numberNames.elementAt(2));
//
//        KLog.e("共有元素：" + numberNames);


//        Stack stack = new Stack();
//
//        stack.push("this is a stack");
//        stack.push(new Date());
//        stack.push(11111);
//        stack.push(1.23456);
//
//
//        KLog.e("堆栈初始大小为： " + stack.size());
//
//        KLog.e("pop一个元素为： " + stack.pop());
//        KLog.e("弹出一个元素后的堆栈大小为：" + stack.size());
//
//        KLog.e("第一个元素为：" + stack.peek());
//        KLog.e("peek后的堆栈大小：" + stack.size());
//        KLog.e("this is a stack 距离栈顶的距离为：" + stack.search("this is a stack"));
//
//        KLog.e("遍历元素：");
//        while (!stack.isEmpty()) {
//            KLog.e("元素为：" + stack.pop());
//        }


//        Vector numberNames = new Vector();
//        numberNames.add("zero");
//        numberNames.add("two");
//        numberNames.add("three");
//        numberNames.add("four");
//        numberNames.add("five");
//        numberNames.add("six");
//        numberNames.add("seven");
//        numberNames.add("eight");
//        numberNames.add("nine");
//        numberNames.add("ten");
//
//        Enumeration numbers = numberNames.elements();
//
//        while (numbers.hasMoreElements()) {
//            KLog.e("numbers.nextElement() is " + numbers.nextElement());
//        }


//        ArrayList arrayList = new ArrayList();
//        arrayList.add("zero");
//        arrayList.add("one");
//        arrayList.add("two");
//
//        Iterator iterator = arrayList.iterator();
//        KLog.e("arrayList中的元素为：");
//        while (iterator.hasNext()) {
//            String num = (String) iterator.next();
//            KLog.e("num is " + num);
//        }
//
//        Vector vector = new Vector();
//        vector.addElement("first");
//        vector.addElement("second");
//        vector.addElement("third");
//
//        iterator = vector.iterator();
//        KLog.e("Vector中的元素为：");
//
//        while (iterator.hasNext()) {
//            KLog.e(" iterator.next()" + iterator.next());
//        }

//        TreeSet treeSet = new TreeSet();
//        treeSet.add("dog");
//        treeSet.add("cat");
//        treeSet.add("pig");
//        treeSet.add("sheep");
//        treeSet.add("chick");
//
//
//        Iterator iterator = treeSet.iterator();
//        while (iterator.hasNext()) {
//            String animal = (String) iterator.next();
//
//            KLog.e("animal is " + animal);
//        }


//        HashMap hashMap = new HashMap();
//        hashMap.put(14, new Card(14, "红桃", 1));
//        hashMap.put(10, new Card(10, "黑桃", 10));
//        hashMap.put(22, new Card(22, "大王", 0));
//        hashMap.put(50, new Card(50, "方块", 13));
//
//        KLog.e("遍历所有值：");
//        Collection values = hashMap.values();
//        for (Object obj : values) {
//            KLog.e("obj is " + obj);
//        }
//
//        KLog.e(" hashMap.get(50) is " + hashMap.get(50));

//        File path = getFilesDir();
//        String pathStr = path.getAbsolutePath();
//        String testFilePath = pathStr + "/wang.txt";
//
//        KLog.e("testFilePath is " + testFilePath);
//
//        File testFile = new File(testFilePath);
//
//        byte b[] = {123,124,125,126,23,45,21};
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(testFile);
//            FileInputStream fileInputStream = new FileInputStream(testFile);
//
//            for (int i = 0; i < b.length; i++) {
//                fileOutputStream.write(b[i]);
//            }
//
//            int ch = fileInputStream.read();
//
//            while (ch != -1) {
//                KLog.e("ch is " + ch);
//
//                ch = fileInputStream.read();
//            }
//
//            fileInputStream.close();
//            fileOutputStream.close();
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            KLog.e("完成了哦");
//        }


//        E_thread1 thread1 = new E_thread1("线程1", 1);
//        E_thread1 thread2 = new E_thread1("线程2", 0);
//
//        thread1.start();
//        thread2.start();
//
//        KLog.e("活动线程个数为" + thread2.activeCount());

//        E_runnable1 runnable1 = new E_runnable1(1);
//        E_runnable1 runnable2 = new E_runnable1(0);
//        Thread thread1 = new Thread(runnable1);
//        Thread thread2 = new Thread(runnable2);
//
//        thread1.start();
//        thread2.start();
//
//        KLog.e("活动线程数为： " + thread2.activeCount());


//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(new SubThread());


//        mBtnStartThread.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Message message = new Message();
//                Bundle bundle = new Bundle();
//                bundle.putString("hh", mEtTestThread.getText().toString());
//
//                message.what = 1;
//                message.setData(bundle);
//
//                myThread.mHander.sendMessage(message);
//            }
//        });
//
//        myThread = new MyThread();
//        myThread.start();

//        ThreadPriority t1 = new ThreadPriority("1");
//        ThreadPriority t2 = new ThreadPriority("2");
//        ThreadPriority t3 = new ThreadPriority("3");
//        KLog.e("设置线程优先级");
//        t1.setPriority(Thread.MIN_PRIORITY);
//        t2.setPriority(Thread.MAX_PRIORITY);
//
//        t1.start();
//        t2.start();
//        t3.start();


//        Lefthand lefthand = new Lefthand();
//        lefthand.setPriority(Thread.MAX_PRIORITY);
//
//        Righthand righthand = new Righthand();
//        righthand.setPriority(Thread.MIN_PRIORITY);
//
//        lefthand.start();
//        righthand.start();


//        Fab t = new Fab();
//        t.start();
//
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        KLog.e("主线程中的字符串");


//        YieldThread t1 = new YieldThread("1");
//        YieldThread t2 = new YieldThread("2");
//        YieldThread t3 = new YieldThread("3");
//
//        KLog.e("线程 "+t1.getName()+"执行了让步");
//        t1.yield();
//
//        t1.start();
//        t2.start();
//        t3.start();

//        Mouse jerry = new Mouse("Jerry");
//        jerry.start();
//        jerry.tom.start();

//        Ticket ticket = new Ticket();
//
//        Buy b1 = new Buy(ticket, 10);
//        Buy b2 = new Buy(ticket, 5);
//
//        b1.start();
//        b2.start();

//        Ticket ticket = new Ticket();
//        ticket.buyer1.start();
//        ticket.buyer2.start();



//        SyncStack syncStack = new SyncStack();
//        Producer producer = new Producer(syncStack);
//        Consumer consumer = new Consumer(syncStack);
//
//        new Thread(producer).start();
//        new Thread(consumer).start();

//        ArrayList a = new ArrayList();
//        E_getClass e_getClass = new E_getClass();
//        e_getClass.showInterface(a);
//
//        Button button = new Button(this);
//        e_getClass.showInterface(button);

//        Class c;
//        Class[] iface;
//
//        try {
//            c = Class.forName("java.util.List");
//            iface = c.getInterfaces();
//            KLog.e("类" + c.getName() + "继承的接口有：");
//
//            for (int i = 0; i < iface.length; i++) {
//                KLog.e(iface[i].getName() + "\n");
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


//        Student s1 = new Student("张三", 23, '男');
//        Class c = s1.getClass();

//        Field[] fields = c.getFields();
//        KLog.e("类Student中的公共成员变量共有：");
//        for (int i=0; i < fields.length; i++) {
//            KLog.e(fields[i]);
//        }
//
//        fields = c.getDeclaredFields();
//        KLog.e("类Student中的所有成员变量共有：");
//        for (int i=0; i < fields.length; i++) {
//            KLog.e(fields[i]);
//        }
//
//
//        KLog.e("对象s1中公共成员变量name的值：");
//        try {
//            KLog.e((c.getField("name")).get(s1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        Student s1 = new Student("张三", 23, '男');
//        Class c = s1.getClass();
//
//        Method[] f1 = c.getMethods();
//        KLog.e("类Student中的公共成员方法：");
//        for (int i=0; i < f1.length; i++) {
//            KLog.e(f1[i]);
//        }
//
//        f1 = c.getDeclaredMethods();
//        KLog.e("类Student中的所有成员方法(不包括继承的)：");
//        for (int i=0; i < f1.length; i++) {
//            KLog.e(f1[i]);
//        }
//
//        Constructor[] f2 = c.getDeclaredConstructors();
//        KLog.e("类Student中的所有构造方法：");
//        for (int i=0; i < f2.length; i++) {
//            KLog.e(f2[i]);
//        }
//
//
//        Class[] c2 = {String.class};
//        Method m1 = null;
//        KLog.e("Student中公共成员方法setCountry的信息：");
//        try {
//            m1 = c.getMethod("setCountry", c2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        KLog.e(m1 + "返回值类型" + m1.getReturnType() + "参数");
//
//        for (int i = 0; i < (m1.getParameterTypes()).length; i++) {
//            KLog.e(m1.getParameterTypes()[i]);
//        }

        Student s1 = new Student("张三", 23, '男');
        Class c = s1.getClass();
        Method m1 = null;
        Class[] c2 = null;
        Object[] o = null;
        Field field = null;
        Class[] c3 = {String.class, int.class, char.class};
        Constructor constructor = null;
        Object[] o2 = {"王五", 19, '女'};

        try {
            m1 = c.getMethod("display", c2);
            m1.invoke(s1, o);

            field = c.getField("name");
            field.set(s1, "李四");
            field = c.getField("name");

            String s = (String) field.get(s1);
            KLog.e("修改后的名字为" + s);


            Student s2 = (Student) c.newInstance();
            s2.display();
            constructor = c.getConstructor(c3);

            Student s3 = (Student) constructor.newInstance(o2);
            s3.display();

        } catch (Exception e) {
            e.printStackTrace();
        }





  }


  class Student extends Object {
        public String name;
        protected int age;
        char sex;
        public String country = "中国";

        public Student() {
        }

        public Student(String name, int age, char sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

      public void setCountry(String country) {
          this.country = country;
      }


      public void display() {
            KLog.e("name:" + name + "age:" + age + "sex:" + sex + "country:" + country);
      }

      public String getName() {
          return name;
      }
  }



  class E_getClass{
        Class c;

        public boolean showInterface(Object obj) {
            if (null == obj) {
                return false;
            }

            c = obj.getClass();
            Class[] iface = c.getInterfaces();

            KLog.e("类" + c.getName() + "继承的接口有：");
            for (int i = 0; i < iface.length; i++) {
                KLog.e(iface[i].getName() + "\n");
            }

            return true;
        }

    }


  class SyncStack {
        private int index = 0;
        private  char[] buffer = new char[6];
        public synchronized void push(char c) {
            while (index == buffer.length) {
                try
                {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.notify();
            buffer[index] = c;
            index++;

            KLog.e("生产了：" + c);
        }

        public synchronized char pop() {
            while (0 == index) {
                try
                {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.notify();
            index--;
            KLog.e("    消费了：" + buffer[index]);

            return buffer[index];
        }


  }


  class Producer implements Runnable {
        private SyncStack syncStack = null;

        public Producer(SyncStack s) {
            this.syncStack = s;
        }

      @Override
      public void run() {
          for (int i = 0; i<=10; i++) {
              char c = (char)(Math.random() * 26 + 'A');

              syncStack.push(c);
          }

          try {
              Thread.sleep((int)(Math.random() * 300));
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }

  class Consumer implements Runnable {
        private SyncStack syncStack = null;

        public Consumer(SyncStack s) {
            this.syncStack = s;
        }

      @Override
      public void run() {
          for (int i = 0; i <= 10; i++) {
              char c = syncStack.pop();

              try
              {
                  Thread.sleep((int)(Math.random() * 800));
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }


  class Ticket  implements Runnable {
        int amount = 100;
        Thread buyer1, buyer2;

        public Ticket() {
            buyer1 = new Thread(this);
            buyer1.setName("买票者 1");

            buyer2 = new Thread(this);
            buyer2.setName("买票者 2");
        }

      @Override
      public void run() {
          if (Thread.currentThread() == buyer1) {
              getTicket(10);
          } else if (Thread.currentThread() == buyer2) {
              getTicket(5);
          }
      }

      void setAmount(int num) {
            amount = amount - num;
        }

        int getAmount() {
            return amount;
        }

       synchronized void getTicket(int num) {
            if (0 >= amount) {
                KLog.e("票已售完");
            } else {
                KLog.e("现有票" + amount + "张   买票" + num + "张");

                if (Thread.currentThread() == buyer1) {
                    amount = amount - num;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                } else if (Thread.currentThread() == buyer2) {
                    amount = amount - num;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }

        }
  }

  class Buy extends Thread {
        private Ticket ticket;
        private int num;

        Buy(Ticket ticket,  int count) {
            this.ticket = ticket;
            num = count;
        }

      @Override
      public void run() {
          synchronized (ticket) {
              KLog.e("现有" + ticket.getAmount() + "张");
              ticket.getTicket(num);

              try {
                  sleep(1);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              ticket.setAmount(num);
          }
      }
  }


  class Cat extends Thread {
        Cat(String msg) {
            super(msg);

            KLog.e("线程" + msg + "被创建");
        }

      @Override
      public void run() {
          try {
              KLog.e("休息10秒后再回答");
              sleep(10000);
          } catch (InterruptedException e) {
              KLog.e(getName() + "被吵醒了");

              return;
          }

          KLog.e("我跟Jerry拼了");
      }
  }

  class Mouse extends Thread {
        Cat tom  = new Cat("Tom");

        Mouse(String msg) {
            super(msg);

            KLog.e("线程" + msg + "被创建");
        }

      @Override
      public void run() {
          for (int i = 1; i <= 5; i++) {
              KLog.e("Tom！");

              try {
                  sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }

          tom.interrupt();
      }
  }


  class YieldThread extends Thread {
        YieldThread(String id) {
            super(id);

            KLog.e("线程" + id + "被创建");
        }


      @Override
      public void run() {
          long start = new Date().getTime();
          for (int i = 0; i < 2000; i++) {
              double sum = 0.0d;

              for (int j = 1; j < 1000; j++) {
                  sum = sum + i;
              }
          }

          long end = new Date().getTime();
          KLog.e("线程" + this.getName() + "执行时间：" + (end - start) + "毫秒");
      }
  }


  class Fab extends Thread {

        public long f(long n) {
            long c = 0;
            if (n == 1 || n == 2) {
                c = 1;
            } else if (n >= 3) {
                c = f(n-1) + f(n -2);
            }

            return c;
        }


      @Override
      public void run() {
          for (int i = 20; i <= 25; i++) {
              KLog.e("斐波那契数列第" + i + "项为： " + f(i));
          }

          KLog.e("结束计算");
      }
  }




  class Lefthand extends Thread {
      @Override
      public void run() {
          for (int i = 0; i < 5; i++) {
              KLog.e("A");

              try {
                  sleep(600);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }

    class Righthand extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                KLog.e("B");

                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


  class ThreadPriority extends Thread {

        public ThreadPriority(String id) {
            super(id);

            KLog.e("线程" + id + "被创建");
        }


      @Override
      public void run() {
          KLog.e("线程" + getName() + "开始运行");

          for (int i = 1; i < 4; i++) {
              KLog.e("线程" + getName() + "的优先级为：" + getPriority());
          }

          KLog.e("线程" + getName() + "结束");
      }
  }



  private class MyThread extends Thread {
        public Handler mHander;

      @Override
      public void run() {
          super.run();

          Looper.prepare();

          mHander = new Handler(new Handler.Callback() {
              @Override
              public boolean handleMessage(Message msg) {
                  switch (msg.what) {
                      case 1:
                      {
                          KLog.e("data is " + msg.getData().getString("hh"));
                      }
                      break;
                      default:
                          break;
                  }

                  return false;
              }

          });

          Looper.loop();
      }
  }


  private Handler mHandler = new Handler() {
      @Override
      public void handleMessage(Message msg) {
          switch (msg.what) {
              case 0:
              {
                  String message = System.currentTimeMillis() + ":" + "获取到来自子线程的消息为：" + msg.what;
                  mTvHandler.setText(message);
              }
                  break;
              case 1:
              case 2:
              case 3:
              case 4:
              case 5:
              case 6:
              case 7:
              case 8:
              case 9:
              {
                  String message = mTvHandler.getText().toString() + "\n" + System.currentTimeMillis() + ":" + "获取到来自子线程的消息为：" + msg.what;
                  mTvHandler.setText(message);
              }
                  break;

              case FINISH_CHILD_THREAD: {
                  String message = mTvHandler.getText().toString() + "\n" + System.currentTimeMillis() + ":" + "子线程已执行完毕";
                  mTvHandler.setText(message);

                  mBtnStartThread.setEnabled(true);
              }
                  break;

              case CHILD_THREAD_EXCEPTION: {
                  String message = mTvHandler.getText().toString() + "\n" + System.currentTimeMillis() + ":" + "子线程出现异常";
                  mTvHandler.setText(message);

                  mBtnStartThread.setEnabled(false);
              }
                  break;
          }
      }
  };


    class SubThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                mHandler.obtainMessage(i).sendToTarget();

                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    mHandler.obtainMessage(CHILD_THREAD_EXCEPTION).sendToTarget();
                }
            }

            mHandler.obtainMessage(FINISH_CHILD_THREAD).sendToTarget();
        }
    }





  class E_runnable1 implements Runnable {
        int flag;

        public E_runnable1(int f) {
            flag = f;
        }

      @Override
      public void run() {
          KLog.e("");
          if (0 == flag) {
              for (int i = 0; i <= 9; i++) {
                  KLog.e("i is " + i);
              }
          } else if (1 == flag) {
              for (int j  = '0'; j <= '9'; j++) {
                  KLog.e("j is " + j);
              }
          }
      }
  }



  class E_thread1 extends Thread {
        int flag;

        public E_thread1(String name, int f) {
            super(name);

            flag = f;
        }

      @Override
      public void run() {
          KLog.e(getName() + " 启动： ");
          if (0 == flag) {
              for (int i = 0; i <= 9; i++) {
                  KLog.e("i is " + i);
              }
          } else if (1 == flag) {
              for (int j  = '0'; j <= '9'; j++) {
                  KLog.e("j is " + j);
              }
          }

          KLog.e(getName() + "结束！");
      }
  }


  class Card implements Comparable {

        int no;
        String pattern;
        int num;

        public Card(int no, String pattern, int num) {
            this.no = no;
            this.pattern = pattern;
            this.num = num;
        }

      @Override
      public String toString() {
          String s = "编号：" + no + "\t 花色" + pattern + "\t 点数： " + num;

          return s;
      }

      @Override
      public int compareTo(@NonNull Object o) {
          return (no - ((Card)o).no);
      }

      @Override
      public boolean equals( Object obj) {
          boolean f = false;
          if (obj instanceof Card) {
              if (no == ((Card)obj).no) {
                  f = true;
              }
          }

          return f;
      }
  }




    private synchronized void testANR() {
        SystemClock.sleep(30 * 1000);
    }

    private synchronized void initView() {

    }

    private ViewStub.OnInflateListener inflateListener = new ViewStub.OnInflateListener() {
        @Override
        public void onInflate(ViewStub stub, View inflated) {
            Toast.makeText(MergeActivity.this, "ViweStub is loaded", Toast.LENGTH_SHORT).show();
        }
    };


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                {
                    try {
                        LinearLayout linearLayout = (LinearLayout) mStub.inflate();
                        RatingBar bar = linearLayout.findViewById(R.id.ratingBar1);
                        bar.setNumStars(4);
                    } catch (Exception e) {
                        e.printStackTrace();

                        mStub.setVisibility(View.VISIBLE);
                    }
                }
                    break;

                case R.id.btn2:
                {
                    mStub.setVisibility(View.GONE);
                }
                break;

                case R.id.btn3:
                {
                    LinearLayout linearLayout = findViewById(R.id.inflatedStart);
                    RatingBar ratingBar = linearLayout.findViewById(R.id.ratingBar1);
                    float numStart = ratingBar.getRating();
                    numStart++;

                    if (numStart > 4) {
                        numStart = 0;
                    }

                    ratingBar.setRating(numStart);
                }
                break;
            }
        }
    };







}
