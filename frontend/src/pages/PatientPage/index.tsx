import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import type { Patient } from "../../types/patient"

export default function Patient() {
    const { id } = useParams();
    const [patient, setPatient] = useState<Patient | null>(null);
    
    useEffect(() => {
    fetch(`http://localhost:8080/api/patients/${id}`)
        .then(res => res.json())
        .then(data => setPatient(data))
        .catch(err => console.error(err));
    }, [id]);

    return (<>
        <div className="max-w-sm bg-white rounded-2xl shadow-lg p-5">
    
            <div className="flex items-center gap-3 mb-4">
                <div className="w-12 h-12 rounded-full bg-gray-200 flex items-center justify-center font-bold text-gray-700">
                JD
                </div>

                <div>
                <h2 className="text-lg font-semibold text-gray-800">{ patient?.fullName }</h2>
                <p className="text-xs text-gray-500">ID: { patient?.id }</p>
                </div>
            </div>

            <div className="space-y-2 text-sm text-gray-700">
                <div className="flex justify-between">
                <span className="text-gray-400">Age:</span>
                <span>{ patient?.age }</span>
                </div>

                <div className="flex justify-between">
                <span className="text-gray-400">Gender:</span>
                <span>{ patient?.gender }</span>
                </div>

                <div className="flex justify-between">
                <span className="text-gray-400">Blood type:</span>
                <span></span>
                </div>

                <div className="flex justify-between">
                <span className="text-gray-400">Phone number:</span>
                <span>{ patient?.phoneNumber }</span>
                </div>
            </div>

            <div className="mt-4">
                <span className="inline-block px-3 py-1 text-xs font-semibold rounded-full bg-green-100 text-green-700">
                </span>
            </div>

        </div>
</>)
}