from kafka import KafkaProducer
import schedule
import time
import json

producer = KafkaProducer(bootstrap_servers = 'localhost:9092')
msg_cnt = 0
sources = {0: "asdf", 1: "BASDF", 2: "third"}

def produce_msg():
    global msg_cnt
    msg = {
        "message": f"message count {msg_cnt}",
        "subthing": {
            "submessage": f"{msg_cnt%10}",
            "others": [
                {
                    "modulo": f"{msg_cnt%11}" 
                },
                {
                    "modulo": f"{msg_cnt%21}" 
                },
                {
                    "modulo": f"{msg_cnt%31}" 
                }
            ],
            "porps": {
                sources[msg_cnt%3]: {
                    "id": msg_cnt%10
                },
                sources[msg_cnt%2]: {
                    "id": msg_cnt%4
                }
            }
        }
    }
    print(msg_cnt, msg)
    producer.send("first-topic", value=bytes(json.dumps(msg), encoding="utf-8"), 
                  key=bytes(f"testkey{msg_cnt%10}", 'utf-8'), 
                  headers=[('retrycount', int(0).to_bytes(2,'big')), ('targettopic', bytes('output_topic', 'utf-8'))])
    msg_cnt += 1

schedule.every(2).seconds.do(produce_msg)
while True:
    schedule.run_pending()
    time.sleep(1)

