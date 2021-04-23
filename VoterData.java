public class VoterData implements Hashtable {
	private Data[] data = new Data[101];
	private int size;
	private int used;
	private float loadFactor=0.75F;
	private final Data NIL = new Data(null, null);

	class Data {
		Object data;
		Object key;

		Data(Object id, Object data) {
			this.data = data;
			key = id;

		}

	}

	private int hash(Object key) {
		if (key == null)
			throw new IllegalArgumentException();
		return (key.hashCode() % 0xFFFFFFF) % data.length;
	}

	private int nextProbe(int k, int i) {

		return (k + i) % data.length;
	}

	@Override
	public Object put(Object key, Object value) {
		// Koded by 19sw10
		
		if(used > loadFactor*data.length) rehash();
		int h = hash(key);
		for (int i = 0; i < data.length; i++) {
			int j = nextProbe(h, i);
			Data entry = data[j];
			if (entry == null) {
				data[j] = new Data(key, value);
				++size;
				++used;
				return null;
			}
			if (entry == NIL)
				continue;
			if (entry.key.equals(key)) {
				Object oldValue = entry.data;
				data[j].data = data;
				return oldValue;
			}
		}

		return null;
	}

	@Override
	public Object get(Object key) {
		// Koded by 19sw10
		int h = hash(key);
		for (int i = 0; i < data.length; i++) {
			int j = nextProbe(h, i);
			Data entry = data[j];
			if (entry == null)
				break;
			if (entry == NIL)
				continue;
			if (entry.key.equals(key))
				return entry.data;
		}
		return null;

	}

	@Override
	public boolean Contain(Object key) {
		// Koded by 19sw10
		return false;
	}

	@Override
	public int size() {
		// Koded by 19sw10
		return size;
	}
   
	private void rehash() {
		Data[] oldEntries = data;
		data = new Data[2 * oldEntries.length + 1];
		for (int k = 0; k < oldEntries.length; k++) {
			Data entry = oldEntries[k];
			if (entry == null || entry == NIL)
				continue;
			int h = hash(entry.key);
			for (int i = 0; i < data.length; i++) {
				int j = nextProbe(h, i);
				if (data[j] == null) {
					data[j] = entry;
					break;
				}
			}
		}
		used = size;
	}
	void printlist() {
		
			
			for(int i=0;i<data.length;i++) {
				if(data[i]!=null) 
					System.out.println("position  at "+i+"value "+data[i].data);
					else
						System.out.println("empty at"+i);
					
				
				
			
		}
	}


}
