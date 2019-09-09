import json
import sys
import threading

lock = threading.Lock()

# To fill in streets already analyzed
def repetitions(street_dict, street_name, language):
    for street in street_dict['features']:
        if 'name' in street['properties']:
            if street['properties']['name'] == street_name: 
                with lock:
                    street['properties'].update({'language' : language}) 

with open(sys.argv[1]) as f:
    data = json.load(f)
    for street in data['features']: 
        if 'language' not in street['properties'] \
                and 'name' in street['properties']:
            street_name = street['properties']['name']
            print(street_name)
            x = input("1 Eng, 2 Chi, 3 or other Neutral: ")
            if x == "break":
                break
            language = "Eng" if x == '1' else ("Chi" if x == '2' else "Neutral")
            street['properties'].update(
                    {'language' : language})
            threading.Thread(target=repetitions, args=(data, street_name, language)).start()

with open(sys.argv[1].split('.')[0] + '-analyzed.geojson', 'w') as t:
    json.dump(data, t, indent=2)
